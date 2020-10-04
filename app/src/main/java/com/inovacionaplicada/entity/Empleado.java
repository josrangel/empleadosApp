package com.inovacionaplicada.entity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Empleado {
    @SerializedName("empleado_id")
    @Expose
    private int empleado_id;
    @SerializedName("empleado_nombre")
    @Expose
    private String empleado_nombre;
    @SerializedName("empleado_apellidoPaterno")
    @Expose
    private String empleado_apellidoPaterno;
    @SerializedName("empleado_apellidoMaterno")
    @Expose
    private String empleado_apellidoMaterno;
    @SerializedName("empleado_telefono")
    @Expose
    private String empleado_telefono;
    @SerializedName("empleado_fechaNacimiento")
    @Expose
    private String empleado_fechaNacimiento;
    @SerializedName("empleado_area")
    @Expose
    private int empleado_area;
    @SerializedName("empleado_email")
    @Expose
    private String empleado_email;

    public Empleado(int empleado_id, String empleado_nombre, String empleado_apellidoPaterno, String empleado_apellidoMaterno, String empleado_telefono, String empleado_fechaNacimiento, int empleado_area, String empleado_email) {
        this.empleado_id = empleado_id;
        this.empleado_nombre = empleado_nombre;
        this.empleado_apellidoPaterno = empleado_apellidoPaterno;
        this.empleado_apellidoMaterno = empleado_apellidoMaterno;
        this.empleado_telefono = empleado_telefono;
        this.empleado_fechaNacimiento = empleado_fechaNacimiento;
        this.empleado_area = empleado_area;
        this.empleado_email = empleado_email;
    }

    public int getEmpleado_id() {
        return empleado_id;
    }

    public void setEmpleado_id(int empleado_id) {
        this.empleado_id = empleado_id;
    }

    public String getEmpleado_nombre() {
        return empleado_nombre;
    }

    public void setEmpleado_nombre(String empleado_nombre) {
        this.empleado_nombre = empleado_nombre;
    }

    public String getEmpleado_apellidoPaterno() {
        return empleado_apellidoPaterno;
    }

    public void setEmpleado_apellidoPaterno(String empleado_apellidoPaterno) {
        this.empleado_apellidoPaterno = empleado_apellidoPaterno;
    }

    public String getEmpleado_apellidoMaterno() {
        return empleado_apellidoMaterno;
    }

    public void setEmpleado_apellidoMaterno(String empleado_apellidoMaterno) {
        this.empleado_apellidoMaterno = empleado_apellidoMaterno;
    }

    public String getEmpleado_telefono() {
        return empleado_telefono;
    }

    public void setEmpleado_telefono(String empleado_telefono) {
        this.empleado_telefono = empleado_telefono;
    }

    public String getEmpleado_fechaNacimiento() {
        return empleado_fechaNacimiento;
    }

    public void setEmpleado_fechaNacimiento(String empleado_fechaNacimiento) {
        this.empleado_fechaNacimiento = empleado_fechaNacimiento;
    }

    public int getEmpleado_area() {
        return empleado_area;
    }

    public void setEmpleado_area(int empleado_area) {
        this.empleado_area = empleado_area;
    }

    public String getEmpleado_email() {
        return empleado_email;
    }

    public void setEmpleado_email(String empleado_email) {
        this.empleado_email = empleado_email;
    }

    @Override
    public String toString() {
        return "{\"empleado_id\":" + empleado_id +
                ", \"empleado_nombre\":\"" + empleado_nombre + '\"' +
                ", \"empleado_apellidoPaterno\":\"" + empleado_apellidoPaterno + '\"' +
                ", \"empleado_apellidoMaterno\":\"" + empleado_apellidoMaterno + '\"' +
                ", \"empleado_telefono\":\"" + empleado_telefono + '\"' +
                ", \"empleado_fechaNacimiento\":\"" + empleado_fechaNacimiento + '\"' +
                ", \"empleado_area\":" + empleado_area +
                ", \"empleado_email\":\"" + empleado_email + '\"' +
                '}';
    }
}
