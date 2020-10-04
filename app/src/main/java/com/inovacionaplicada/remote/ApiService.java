package com.inovacionaplicada.remote;
import com.inovacionaplicada.entity.ArrayEmpleados;
import com.inovacionaplicada.entity.Empleado;
import com.inovacionaplicada.entity.Respuesta;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @POST("empleados.php")
    @FormUrlEncoded
    Call<Respuesta> savePost(@Field("empleados") ArrayEmpleados empleados);
    //Call<ArrayList<Empleado>> savePost(String respuesta);
}
