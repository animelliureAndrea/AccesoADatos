/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Diurno
 */
public class Empleado implements Serializable{
    private String nombre;
    private int dep;

    public Empleado() {//tu siempre para el besito
    }

    public Empleado(String nombre, int dep) {
        this.nombre = nombre;
        this.dep = dep;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDep() {
        return dep;
    }

    public void setDep(int dep) {
        this.dep = dep;
    }

    @Override
    public String toString() {
        return "Empleado{" + "nombre=" + nombre + ", dep=" + dep + '}';
    }
    
    
}
