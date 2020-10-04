package com.inovacionaplicada.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Respuesta {
    @SerializedName("estado")
    @Expose
    private int estado;

    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    public Respuesta(int estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
