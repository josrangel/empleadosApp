package com.inovacionaplicada.remote;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.inovacionaplicada.ConexionLocal;
import com.inovacionaplicada.MainActivity;
import com.inovacionaplicada.entity.ArrayEmpleados;
import com.inovacionaplicada.entity.Empleado;
import com.inovacionaplicada.entity.Respuesta;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sincronizacion {

    private ConexionLocal con=new ConexionLocal();
    private ApiService miApiService=ApiUtils.getAPIService();

    public void ejecutaSincronizacion(Context context){
        ArrayList<Empleado> empleados=con.listaEmpleados(context);
        if(empleados.isEmpty()){
            Toast.makeText(context,"No hay datos en el celular para sincronizar",Toast.LENGTH_SHORT).show();
        }else{
            ArrayEmpleados emp=new ArrayEmpleados(empleados);
            sendPost(context, emp);
        }
    }

    public void sendPost(final Context context, ArrayEmpleados empleados) {
        //JsonReader.setLenient(true);
        Toast.makeText(context,"Conectando al servidor...",Toast.LENGTH_SHORT).show();;
        miApiService.savePost(empleados).enqueue(new Callback<Respuesta>() {

            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                if(response.isSuccessful()) {
                    //Log.i("Sincr1", response.message());
                    Log.i("Sincr1", response.message());
                    //Log.i("Sinc", response.body());
                    Toast.makeText(context,response.body().getMensaje(),Toast.LENGTH_SHORT).show();;


                }else{
                    Log.i("Sincr2", response.message());
                    Toast.makeText(context,response.body().getMensaje(),Toast.LENGTH_SHORT).show();;
                }

            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Log.i("Sincf", t.toString());
                Toast.makeText(context,"No se pudo realizar la sincronizacion",Toast.LENGTH_SHORT).show();;
            }
        });
    }
}
