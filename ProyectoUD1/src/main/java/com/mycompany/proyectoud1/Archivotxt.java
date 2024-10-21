/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoud1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrea
 */
public class Archivotxt {
    Scanner sc;

    public Archivotxt() {
        sc = new Scanner (System.in);
    }
    
    
    public void transicion(String cambio, File f){
        if(cambio.equalsIgnoreCase(".txt")){
            cambioTxt(f);
        }else if(cambio.equalsIgnoreCase(".dat")){
            cambioDat(f);
        }else if(cambio.equalsIgnoreCase(".properties")){
            Map<String, String>propiedades = cambioPropi(f);
        }else if(cambio.equalsIgnoreCase(".xml")){
            System.out.println("Nada de momento");
        }
    }
    
    public void cambioTxt(File f){
        //txt: se copiará y pegará en uno nuevo, hay que pedir el nombre.
            System.out.println("Dame el nombre del archivo en el que lo pegaremos");
            String nombre = sc.nextLine();
            BufferedReader lee=null;
            BufferedWriter escribe =null;
                    
            try {
                lee = new BufferedReader(new FileReader(f));
                escribe = new BufferedWriter(new FileWriter(nombre));
                String linea = lee.readLine();
                while(linea!=null){
                    escribe.write(linea);
                    escribe.newLine();
                    linea = lee.readLine();
                }
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    lee.close();
                    escribe.close();
                } catch (IOException ex) {
                    Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
    
    private void cambioDat(File f){
        //dat: se copiará a un fichero binario sin saber tipos, sólo byte a byte.
        System.out.println("Dame el nombre del archivo en el que lo pegaremos");
        String nombre = sc.nextLine();
        DataInputStream lee=null;
        DataOutputStream escribe = null;
        
        try {
            lee = new DataInputStream(new FileInputStream(f));
            escribe = new DataOutputStream(new FileOutputStream(nombre));
            
            for(int i =0; i<f.length();i++){
                escribe.write(lee.read());
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                lee.close();
                escribe.close();
            } catch (IOException ex) {
                Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    private Map<String, String> cambioPropi(File f){
        //habrá que comprobar que el fichero txt tenga la estructura clave : valor en todo momento para poder pasarlo, si no, se dirá que no se puede.
        System.out.println("Dame el nombre del archivo en el que lo pegaremos");
        String nombre = sc.nextLine();
        BufferedReader lee = null;
        OutputStream escribe =null;
        boolean control;
        Map<String,String> propiedades=new HashMap<>();
        
        try {//primero comprueba la estructura
            lee=new BufferedReader(new FileReader(f));
            escribe = new FileOutputStream(nombre);
            String linea;
            while ((linea=lee.readLine())!=null){//comprueba linea a linea
                if(!linea.matches("\\s*\\w+\\s*:\\w+\\s*")){
                    control=false; //si alguna linea no cumple el formato false
                }else{
                    control = true;// si si comienza la copia
                    //lee
                    String[] partes= linea.split(":");
                    if(partes.length==2){
                        String clave = partes[0].trim();
                        String valor = partes[1].trim();
                        propiedades.put(clave, valor);
                    }
                    //escritura
                    
                    for(Map.Entry<String, String> entrada: propiedades.entrySet()){
                        String l=entrada.getKey()+":"+entrada.getValue()+"\n";
                        escribe.write(l.getBytes());
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                lee.close();
                escribe.close();
            } catch (IOException ex) {
                Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return propiedades;
    }
}
