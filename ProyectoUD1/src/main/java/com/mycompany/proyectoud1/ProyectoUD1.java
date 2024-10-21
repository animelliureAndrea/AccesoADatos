/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectoud1;


import java.io.File;

import java.util.Scanner;


/**
 * 
 * @author Diurno
 */
public class ProyectoUD1 {


    public static void main(String[] args) {
         Archivotxt txt = new Archivotxt();
         ArchivoDat dat = new ArchivoDat();
         ArchivoXml xml = new ArchivoXml();
        //se le pase por programa una ruta de un archivo
        System.out.println("Pasame la ruta del archivo");
        Scanner sc = new Scanner (System.in);
        String ruta = sc.nextLine();
        File f = new File(ruta);
        if(f.exists()){
            //en primer lugar se pase un archivo txt,
            if(ruta.lastIndexOf(".txt")>0){
                //pedir a qué archivo se quiere pasar:
                System.out.println("¿A que archivo quieres pasarlo?");
                String cambio = sc.nextLine();
                txt.transicion(cambio,f);
            }else if(ruta.lastIndexOf(".dat")>0){
                System.out.println("¿Conoces la estructura del .dat?");
                String conoce = sc.nextLine();
                if(conoce.equalsIgnoreCase("s")){
                    System.out.println("Pásame el txt con la estructura");
                    String rutaTxt = sc.nextLine();
                    System.out.println("¿Que deseas hacer? \n 1-Borrar \n 2-Modificar \n 3-Añadir \n 4-leer");
                    int deseo = sc.nextInt();
                    dat.transicion(deseo, f);
                }else{
                    System.out.println("No podemos hacer nada");
                }
            }else if(ruta.lastIndexOf(".xml")>0){
                //pedir a qué archivo se quiere pasar:
                System.out.println("¿A que archivo quieres pasarlo?");
                String cambio = sc.nextLine();
                xml.cambio(cambio, f);
            }else{
                System.out.println("No válido");
            }
        }else{
            System.out.println("El fichero no existe");
        }
        
        
    }
    

    
    
    
    
    
    
    
    
    
    
    
   
}
