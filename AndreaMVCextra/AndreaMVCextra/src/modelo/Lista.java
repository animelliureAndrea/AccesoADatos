/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrea
 */
public class Lista {
    //aquí estarán los metodos a los que llamará el controlador para mostrar los datos de las listas
    public List<Emple> emples;
    public List<Integer> deps;
    AccesoDAO consultas;

    public Lista() {
        consultas = new AccesoDAO();
        deps = consultas.consultaCMB();
    }
    
    public int llenaEmple(int deptNo){//llenamos la lista de los elementos que devuelve la consulta y el método devuelve el tamaño de la lista para más adelante
        emples = consultas.consultaEmples(deptNo);
        return emples.size();
    }
    
    public String muestraEmple(int i){
        
        //rellenamos un array list nuevo con el método to string previamente sobre escrito en la clase Emple
        ArrayList<String> resultado = new ArrayList<>();
        for(Emple e: emples){
            resultado.add(e.toString());
        }
        return resultado.get(i);
    }
    
    public String muestraDepart(int i){
        //rellenamos un array list con el to string de la clase integer
        ArrayList<String> resultado = new ArrayList<>();
        for(Integer in: deps){
            resultado.add(in.toString());
        }
        return resultado.get(i);
    }

    public List<Emple> getEmples() {
        return emples;
    }

    public List<Integer> getDeps() {
        return deps;
    }
}
