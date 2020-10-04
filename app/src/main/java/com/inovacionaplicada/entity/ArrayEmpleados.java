package com.inovacionaplicada.entity;

import java.util.ArrayList;

public class ArrayEmpleados {
    private ArrayList<Empleado> emp;

    public ArrayEmpleados(ArrayList<Empleado> emp) {
        this.emp = emp;
    }

    public ArrayList<Empleado> getEmp() {
        return emp;
    }

    public void setEmp(ArrayList<Empleado> emp) {
        this.emp = emp;
    }

    @Override
    public String toString() {
        return emp.toString();
    }
}
