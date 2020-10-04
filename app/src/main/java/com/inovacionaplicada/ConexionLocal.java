package com.inovacionaplicada;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.inovacionaplicada.entity.Empleado;
import com.inovacionaplicada.helper.SQLiteEmpleado;

import java.util.ArrayList;

public class ConexionLocal {

    public String ingresaEmpleado(Context context, ContentValues registro){
        try {
            SQLiteDatabase bd=abreBD(context);
            bd.insert("empleado", null, registro);
            bd.close();
        }catch(Exception e) {
            Log.e("Error BD",e.getMessage());
            return "Ocurrio un error "+e;
        }
        return "1i";

    }

    public Empleado listaEmpleado(Context context, int empleado_idC){
        Empleado emp=null;
        try {
            SQLiteDatabase bd=abreBD(context);
            String consulta="SELECT empleado_id, empleado_nombre, empleado_apellidoPaterno, empleado_apellidoMaterno, empleado_telefono, empleado_fechaNacimiento, empleado_area, empleado_email " +
                    "FROM empleado WHERE empleado_id=" + empleado_idC;
            Cursor fila = bd.rawQuery(consulta, null);

            if (fila.moveToFirst()) {

                int empleado_id=new Integer(fila.getString(0));
                String empleado_nombre=fila.getString(1);
                String empleado_apellidoPaterno=fila.getString(2);
                String empleado_apellidoMaterno=fila.getString(3);
                String empleado_telefono=fila.getString(4);
                String empleado_fechaNacimiento=fila.getString(5);
                int empleado_area=new Integer(fila.getString(6));
                String empleado_email=fila.getString(7);
                emp=new Empleado(empleado_id, empleado_nombre, empleado_apellidoPaterno, empleado_apellidoMaterno, empleado_telefono, empleado_fechaNacimiento, empleado_area, empleado_email);

            }
            bd.close();
        }catch(Exception e) {
            Log.println(1,"ErrorCon: ", e.getMessage());
        }
        return emp;
    }

    public ArrayList<Empleado> listaEmpleados(Context context){
        ArrayList<Empleado> empleados = new ArrayList<>();
        try {
            SQLiteDatabase bd=abreBD(context);
            String consulta="SELECT empleado_id, empleado_nombre, empleado_apellidoPaterno, empleado_apellidoMaterno, empleado_telefono, empleado_fechaNacimiento, empleado_area, empleado_email " +
                    "FROM empleado";
            Cursor fila = bd.rawQuery(consulta, null);

            while(fila.moveToNext()) {
                int empleado_id=new Integer(fila.getString(0));
                String empleado_nombre=fila.getString(1);
                String empleado_apellidoPaterno=fila.getString(2);
                String empleado_apellidoMaterno=fila.getString(3);
                String empleado_telefono=fila.getString(4);
                String empleado_fechaNacimiento=fila.getString(5);
                int empleado_area=new Integer(fila.getString(6));
                String empleado_email=fila.getString(7);
                Log.e("Inserta: ",empleado_id+" "+empleado_nombre+" "+empleado_apellidoPaterno+" "+empleado_apellidoMaterno+" "+empleado_telefono+" "+empleado_fechaNacimiento+" "+empleado_area+" "+empleado_email);
                empleados.add(new Empleado(empleado_id, empleado_nombre, empleado_apellidoPaterno, empleado_apellidoMaterno, empleado_telefono, empleado_fechaNacimiento, empleado_area, empleado_email));
            }
            bd.close();
        }catch(Exception e) {
            Log.println(1,"ErrorCon: ", e.getMessage());
        }
        return empleados;
    }

    public String actualizaEmpleado(Context context, ContentValues registro, int empleado_id){
        try {
            SQLiteDatabase bd=abreBD(context);

            int verifica = bd.update("empleado", registro, "empleado_id=" + empleado_id, null);
            bd.close();
            if(verifica==0){
                return "No se pudo actualizar el registro";
            }
        }catch(Exception e) {
            return "Ocurrio un error "+e;
        }
        return "1a";

    }

    public String eliminaEmpleado(Context context, int empleado_id){
        try {
            SQLiteDatabase bd=abreBD(context);
            int verifica = bd.delete("empleado", "empleado_id=" + empleado_id, null);
            bd.close();
            if(verifica==0){
                return "No se puedo eliminar el registro";
            }
        }catch(Exception e) {
            return "Ocurrio un error "+e;
        }
        return "Se elimino el registro";

    }

    private SQLiteDatabase abreBD(Context context){
        SQLiteEmpleado admin = new SQLiteEmpleado(context,Constantes.nombreBDSQLite, null, 1);
        return admin.getWritableDatabase();
    }
}
