/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Andrea
 */
public class Persona {
    String nombre;
    String apellido;
    int numSegSocial;
    int telefono;
    int edad;
    boolean mascota;

    public Persona() {
    }

    public Persona(String nombre, String apellido, int numSegSocial, int telefono, int edad, boolean mascota) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numSegSocial = numSegSocial;
        this.telefono = telefono;
        this.edad = edad;
        this.mascota = mascota;
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

    public int getNumSegSocial() {
        return numSegSocial;
    }

    public void setNumSegSocial(int numSegSocial) {
        this.numSegSocial = numSegSocial;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isMascota() {
        return mascota;
    }

    public void setMascota(boolean mascota) {
        this.mascota = mascota;
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", apellido=" + apellido + ", numSegSocial=" + numSegSocial + ", telefono=" + telefono + ", edad=" + edad + ", mascota=" + mascota + '}';
    }
    
}
