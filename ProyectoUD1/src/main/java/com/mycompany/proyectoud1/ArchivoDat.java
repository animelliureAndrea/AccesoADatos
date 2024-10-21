/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoud1;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Andrea
 */
public class ArchivoDat {
    Scanner sc;
    public void transicion(int deseo, File f){
        switch(deseo){
            case 1:
                borrar(f);
                break;
            case 2:
                modifica(f);
                break;
            case 3:
                anadir(f);
                break;
            case 4:
                leer(f);
                break;
        }
    }
    
    //metodos de .dat
    private void borrar(File f){
        System.out.println("Dame el id a borrar");
        int noid = sc.nextInt();
        //lee el dat, busca el id y borrar
        ObjectInputStream entrada=null;
        List<Alumno> alumnos= new ArrayList<>();//list que almacenara los datos
        //pedimos
        System.out.println("¿A que archivo quieres pasarlo?");
        String cambio = sc.nextLine();
        
        try {//llenamos el array list
            entrada = new ObjectInputStream(new FileInputStream(f));
            while(true){
                try{// Lee cada entrada y agrégala a la lista a no ser que sea el id que queremos borrar
                    Alumno al = (Alumno) entrada.readObject();
                    if(al.getId()!=noid){
                        alumnos.add(al);
                    }
                }catch(EOFException eofe){
                    break;
                }
            }
            
            // Crea un nuevo fichero sin la entrada a borrar
            escribirEnFich(cambio,alumnos);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void modifica(File f){
        System.out.println("¿Que vamos a modificar?");
        String modificacion= sc.nextLine();
        ObjectInputStream entrada=null;
        System.out.println("¿A que archivo quieres pasarlo?");
        String cambio = sc.nextLine();
        List<Alumno> alumnos;
        try {
            entrada = new ObjectInputStream(new FileInputStream(f));
            alumnos = new ArrayList<>();
            while(true){
                try{
                    Alumno alumno =(Alumno) entrada.readObject();
                    alumnos.add(alumno);
                }catch(EOFException eofe){
                    break;
                }
            }
            
            // Dividir la modificación en sus partes (ID, Campo, NuevoValor)
            String[] punti = modificacion.split(" ");
            if(punti.length==3){
                int id = Integer.parseInt(punti[0]);
                String campo = punti[1];
                String nuevoValor = punti[2];
                
                // Modificar el campo del objeto con el ID proporcionado
                for(Alumno alumno:alumnos){
                    if(alumno.getId()==id){
                        if("Nombre".equalsIgnoreCase(campo)){
                            alumno.setNombre(nuevoValor);
                        }
                    }
                }
            }
            
            // Escribir los objetos de vuelta al nuevo archivo
            escribirEnFich(cambio,alumnos);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (entrada != null) {
                try {
                    entrada.close();
                } catch (IOException ex) {
                    Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private void anadir(File f){
        System.out.println("¿Que quieres añadir?");
        String anadi= sc.nextLine();
        System.out.println("¿A que archivo quieres pasarlo?");
        String cambio = sc.nextLine();
        ObjectInputStream entrada = null;
        List<Alumno> alumnos;
        try {
            entrada = new ObjectInputStream(new FileInputStream(f));
            alumnos = new ArrayList<>();
            while(true){
                try{
                    Alumno alumno =(Alumno) entrada.readObject();
                    alumnos.add(alumno);
                }catch(EOFException eofe){
                    break;
                } 
            }
            // Dividir los datos en sus partes (ID, Nombre, Edad)
            String[] punti= anadi.split(" ");
            if(punti.length==2){
                int id = Integer.parseInt(punti[0]);
                String nombre = punti[1];
                
                // Crear un nuevo objeto Alumno y añadirlo a la lista
                Alumno nou = new Alumno(id,nombre);
                alumnos.add(nou);
            }else{
                System.out.println("Formato de datos incorrecto");
            }
            
            // Escribir los objetos de vuelta al nuevo archivo
            escribirEnFich(cambio,alumnos);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void leer(File f){
        System.out.println("¿Que alumno quieres ver? dame su id");
        int veo= sc.nextInt();
        ObjectInputStream entrada = null;
        List<Alumno> alumnos;
        try {
            entrada = new ObjectInputStream(new FileInputStream(f));
            alumnos = new ArrayList<>();
            while(true){
                try{
                    Alumno alumno =(Alumno) entrada.readObject();
                    alumnos.add(alumno);
                }catch(EOFException eofe){
                    break;
                } 
            }
            
            // Buscar al alumno con el ID proporcionado
            for(Alumno alumno:alumnos){
                if(alumno.getId()==veo){
                    System.out.println("Información del alumno con ID " + veo + ":");
                    System.out.println("Nombre: " + alumno.getNombre());
                    return; // Terminar la búsqueda después de encontrar el alumno
                }
            }
             System.out.println("No se encontró ningún alumno con el ID proporcionado.");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
    private void escribirEnFich(String cambio, List<Alumno>alumnos){
        if(cambio.equalsIgnoreCase(".txt")){
                datAtxt(alumnos);
            }else if(cambio.equalsIgnoreCase(".dat")){
                datAdat(alumnos);
            }else if(cambio.equalsIgnoreCase(".xml")){
                datAxml(alumnos);
            }else if(cambio.equalsIgnoreCase(".properties")){
                datApropi(alumnos);
            }
    }

    private void datAtxt(List<Alumno> alumnos) {
        System.out.println("Dame el nombre del archivo en el que lo pegaremos");
        String nombre = sc.nextLine();
        File nuevo = new File(nombre);
        BufferedWriter escribe = null;
        
        try {
            escribe = new BufferedWriter(new FileWriter(nuevo));
            for(Alumno alu:alumnos){
                escribe.write(alu.getId()+" "+alu.getNombre());
                escribe.newLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(escribe!=null){
                try {
                    escribe.close();
                } catch (IOException ex) {
                    Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void datAdat(List<Alumno> alumnos) {
        System.out.println("Dame el nombre del archivo en el que lo pegaremos");
        String nombre = sc.nextLine();
        File nuevo = new File(nombre);
        ObjectOutputStream salida= null;
        try {
            salida= new ObjectOutputStream(new FileOutputStream(nuevo));
            for(Alumno alum:alumnos){
                salida.writeObject(alum);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(salida!=null){
                    salida.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void datAxml(List<Alumno> alumnos) {
        System.out.println("Dame el nombre del archivo en el que lo pegaremos");
        String nombre = sc.nextLine();
        DocumentBuilderFactory fabrica= DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder procesador = fabrica.newDocumentBuilder();
            Document documento = procesador.newDocument();
            Element e = documento.createElement("alumnos");
            documento.appendChild(e);
            
            for(Alumno alum:alumnos){
                Element alumno=documento.createElement("alumno");
                e.appendChild(alumno);
                
                Element id=documento.createElement("id");
                id.appendChild(documento.createTextNode(alum.getNombre()));
                alumno.appendChild(id);
                
                Element nombreEl=documento.createElement("nombre");
                nombreEl.appendChild(documento.createTextNode(alum.getNombre()));
                alumno.appendChild(nombreEl);
            }
            
            TransformerFactory fabricaTrans = TransformerFactory.newInstance();
            Transformer carmen = fabricaTrans.newTransformer();
            DOMSource doms = new DOMSource(documento);
            StreamResult result = new StreamResult(new File(nombre));
            carmen.transform(doms, result);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void datApropi(List<Alumno> alumnos) {
        System.out.println("Dame el nombre del archivo en el que lo pegaremos");
        String nombre = sc.nextLine();
        FileOutputStream salida=null;
        Properties propiedades = new Properties();
        try{
            for(Alumno alum:alumnos){
                propiedades.setProperty("id_"+alum.getId(), alum.getNombre());
            }
            
            salida = new FileOutputStream(nombre);
            propiedades.store(salida, "Alumnos");
            
        }catch (Exception ex) {
        Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(salida!=null){
                    salida.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
