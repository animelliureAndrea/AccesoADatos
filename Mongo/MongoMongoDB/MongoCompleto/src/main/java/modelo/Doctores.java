/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Andrea
 */
public class Doctores {
    String nombre, apellido;
    int dep;
    float salario;

    public Doctores() {
    }

    public Doctores(String nombre, String apellido, int dep, float salario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dep = dep;
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDep() {
        return dep;
    }

    public void setDep(int dep) {
        this.dep = dep;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Doctores{" + "nombre=" + nombre + ", apellido=" + apellido + ", dep=" + dep + ", salario=" + salario + '}';
    }
    
}
