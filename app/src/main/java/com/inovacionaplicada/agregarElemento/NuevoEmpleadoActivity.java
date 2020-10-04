package com.inovacionaplicada.agregarElemento;

import android.content.ContentValues;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.inovacionaplicada.ConexionLocal;
import com.inovacionaplicada.R;
import com.inovacionaplicada.entity.Empleado;
import com.inovacionaplicada.modelview.NuevoElementoViewModel;

public class NuevoEmpleadoActivity extends AppCompatActivity {

    private NuevoElementoViewModel nuevoElementoViewModel;
    private EditText etNombre,etApellidoPaterno, etApellidoMaterno, etTelefono, etFechaNacimiento, etEmail;
    private Spinner spArea;
    private LinearLayout raiz;
    private int empleadoId;
    private boolean actualizacion=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_empleado);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spArea = findViewById(R.id.spArea);
        etNombre=findViewById(R.id.etNombre);
        etApellidoPaterno=findViewById(R.id.etApellidoPaterno);
        etApellidoMaterno=findViewById(R.id.etApellidoMaterno);
        etTelefono=findViewById(R.id.etTelefono);
        etFechaNacimiento=findViewById(R.id.etFechaNacimiento);
        etEmail=findViewById(R.id.etEmail);
        raiz=findViewById(R.id.linearLNuevoEmpleado);

        Bundle parametros = this.getIntent().getExtras();
        if(parametros != null){
            empleadoId = parametros.getInt("empleadoId");
            ConexionLocal con=new ConexionLocal();
            Empleado emp=con.listaEmpleado(getBaseContext(),empleadoId);
            spArea.setSelection(emp.getEmpleado_area());
            etNombre.setText(emp.getEmpleado_nombre());
            etApellidoPaterno.setText(emp.getEmpleado_apellidoPaterno());
            etApellidoMaterno.setText(emp.getEmpleado_apellidoMaterno());
            etTelefono.setText(emp.getEmpleado_telefono());
            etFechaNacimiento.setText(emp.getEmpleado_fechaNacimiento());
            etEmail.setText(emp.getEmpleado_email());
            actualizacion=true;
        }

    }



    public void guardaElemento(View v){
        if(etNombre.getText().toString().trim().isEmpty()){
            Snackbar.make(raiz, "Debe ingresar el nombre", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }else if(etApellidoPaterno.getText().toString().trim().isEmpty()){
            Snackbar.make(raiz, "Debe ingresar el apellido paterno", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }else if(etFechaNacimiento.getText().toString().trim().isEmpty()){
            Snackbar.make(raiz, "Debe ingresar una fecha de nacimiento valida", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }else if(etTelefono.getText().toString().trim().isEmpty()){
            Snackbar.make(raiz, "Debe ingresar un telefono", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }else {
            ejecutaGuardado();
        }
    }

    public void ejecutaGuardado(){
        ConexionLocal con=new ConexionLocal();
        ContentValues cVal= new ContentValues();
        //cVal.put("empleado_id",null);
        String nombre=etNombre.getText().toString();
        String apellidoPaterno=etApellidoPaterno.getText().toString();
        String apellidoMaterno=etApellidoMaterno.getText().toString();
        String telefono=etTelefono.getText().toString();
        String fechaNacimiento=etFechaNacimiento.getText().toString();
        String email=etEmail.getText().toString();
        int area=spArea.getSelectedItemPosition();
        cVal.put("empleado_nombre",nombre);
        cVal.put("empleado_apellidoPaterno",apellidoPaterno);
        cVal.put("empleado_apellidoMaterno",apellidoMaterno);
        cVal.put("empleado_telefono",telefono);
        cVal.put("empleado_fechaNacimiento",fechaNacimiento);
        cVal.put("empleado_area",area);
        cVal.put("empleado_email",email);
        String respuesta="";
        if(actualizacion) {
            respuesta = con.actualizaEmpleado(getBaseContext(), cVal, empleadoId);
        }else{
            respuesta = con.ingresaEmpleado(getBaseContext(), cVal);

        }
        Snackbar.make(raiz, respuesta, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        if (respuesta.equals("1a") || respuesta.equals("1i")){
            this.finish();
        }
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
