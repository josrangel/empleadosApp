package com.inovacionaplicada.agregarElemento;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.inovacionaplicada.R;
import com.inovacionaplicada.modelview.NuevoElementoViewModel;

public class NuevoElementoFragment extends AppCompatActivity {

    private NuevoElementoViewModel nuevoElementoViewModel;
    private EditText etNombre,etApellidoPaterno, etApellidoMaterno, etTelefono, etFechaNacimiento, etEmail;
    private Spinner spArea;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        nuevoElementoViewModel =
                ViewModelProviders.of(this).get(NuevoElementoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        nuevoElementoViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        spArea = findViewById(R.id.spArea);
        etNombre=findViewById(R.id.etNombre);
        etApellidoPaterno=findViewById(R.id.etApellidoPaterno);
        etApellidoMaterno=findViewById(R.id.etApellidoMaterno);
        etTelefono=findViewById(R.id.etTelefono);
        etFechaNacimiento=findViewById(R.id.etFechaNacimiento);
        etEmail=findViewById(R.id.etEmail);


        return root;
    }


}