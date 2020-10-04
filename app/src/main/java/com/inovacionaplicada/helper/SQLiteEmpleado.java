package com.inovacionaplicada.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteEmpleado extends SQLiteOpenHelper {

    public SQLiteEmpleado(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists empleado( " +
                    "empleado_id INTEGER primary key AUTOINCREMENT, " +
                    "empleado_nombre text not null, "+
                    "empleado_apellidoPaterno text not null, "+
                    "empleado_apellidoMaterno text, "+
                    "empleado_telefono text not null, "+
                    "empleado_fechaNacimiento text not null, "+
                    "empleado_area int not null,"+
                    "empleado_email text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists empleado;");

        db.execSQL("create table if not exists empleado( " +
                "empleado_id INTEGER primary key AUTOINCREMENT, " +
                "empleado_nombre text not null, "+
                "empleado_apellidoPaterno text not null, "+
                "empleado_apellidoMaterno text, "+
                "empleado_telefono text not null, "+
                "empleado_fechaNacimiento text not null, "+
                "empleado_area int not null,"+
                "empleado_email text not null);");

    }
}
