package com.inovacionaplicada;

import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.inovacionaplicada.entity.Empleado;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DetalleEmpleadoActivity extends AppCompatActivity {

    private TextView tvArea,tvNombre,tvApellidoPaterno, tvApellidoMaterno, tvTelefono, tvFechaNacimiento, tvEmail, tvEdad;
    private int empleadoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_empleado);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvArea = findViewById(R.id.tvArea);
        tvNombre=findViewById(R.id.tvNombre);
        tvApellidoPaterno=findViewById(R.id.tvApellidoPaterno);
        tvApellidoMaterno=findViewById(R.id.tvApellidoMaterno);
        tvTelefono=findViewById(R.id.tvTelefono);
        tvFechaNacimiento=findViewById(R.id.tvFechaNacimiento);
        tvEmail=findViewById(R.id.tvEmail);
        tvEdad=findViewById(R.id.tvEdad);

        Bundle parametros = this.getIntent().getExtras();
        if(parametros != null){
            empleadoId = parametros.getInt("empleadoId");
            ConexionLocal con=new ConexionLocal();
            Empleado emp=con.listaEmpleado(getBaseContext(),empleadoId);
            String area="Software";
            if (emp.getEmpleado_area()==1){
                area="Consultoria";
            }
            tvArea.setText(area);
            tvNombre.setText(emp.getEmpleado_nombre());
            tvApellidoPaterno.setText(emp.getEmpleado_apellidoPaterno());
            tvApellidoMaterno.setText(emp.getEmpleado_apellidoMaterno());
            tvTelefono.setText(emp.getEmpleado_telefono());
            tvFechaNacimiento.setText(emp.getEmpleado_fechaNacimiento());
            tvEmail.setText(emp.getEmpleado_email());
            try {
                tvEdad.setText(calculaEdad(emp.getEmpleado_fechaNacimiento()));
            }catch(ParseException e) {
                Log.e("Error Fecha: ", e.getMessage());
            }


        }

    }


    public String calculaEdad(String fecha) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",
                Locale.getDefault());
        Date dob = dateFormat.parse(fecha);
        GregorianCalendar cal = (GregorianCalendar) Calendar.getInstance();
        cal.setGregorianChange(new Date(Long.MIN_VALUE));
        cal.clear();
        cal.set(Calendar.YEAR, 0);
        cal.setTimeInMillis( cal.getTimeInMillis() + new Date().getTime() - dob.getTime());

        Formatter fmtr = new Formatter();
        if (cal.get(Calendar.YEAR) > 0) {
            fmtr.format("%d años ", cal.get(Calendar.YEAR));
        }
        return fmtr.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
