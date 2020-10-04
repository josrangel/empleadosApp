package com.inovacionaplicada;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.inovacionaplicada.adapter.EmpleadoAdapter;
import com.inovacionaplicada.entity.Empleado;
import com.inovacionaplicada.agregarElemento.NuevoEmpleadoActivity;

import java.util.ArrayList;

public class EmpleadosFragment extends Fragment {

    private EmpleadosViewModel empleadosViewModel;
    private RecyclerView rvEmpleados;
    private EmpleadoAdapter adapter;
    private ConexionLocal con;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        empleadosViewModel =
                ViewModelProviders.of(this).get(EmpleadosViewModel.class);
        root = inflater.inflate(R.layout.fragment_gallery, container, false);

        FloatingActionButton fab = root.findViewById(R.id.floatingActionButtonNuevoEmpleado);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), NuevoEmpleadoActivity.class);
                startActivity(intent);

            }
        });
        con=new ConexionLocal();

        rvEmpleados=root.findViewById(R.id.rvEmpleados);
        LinearLayoutManager manager=new LinearLayoutManager(root.getContext());
        rvEmpleados.setLayoutManager(manager);


        // specify an adapter with the list to show




        /*final TextView textView = root.findViewById(R.id.text_gallery);
        empleadosViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        ArrayList<Empleado> empleados= con.listaEmpleados(root.getContext());
        //Log.e("Empleados: ",empleados.toString());
        adapter = new EmpleadoAdapter(empleados,root.getContext());
        rvEmpleados.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if(empleados.isEmpty()){
            Snackbar.make(rvEmpleados, "No hay elementos que mostrar", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }



}