/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoud1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Andrea
 */
public class ArchivoXml {
    Scanner sc;
    public void cambio(String cambio, File f){
        if(cambio.equalsIgnoreCase(".txt")){
            System.out.println("Dame el nombre del nuevo fichero");
            String salida = sc.nextLine();
            XMLaTXT xmlAtxt = new XMLaTXT(f);
            xmlAtxt.parseXML(salida);
        }else if(cambio.equalsIgnoreCase(".dat")){
            xmlAdat(f);
        }else if(cambio.equalsIgnoreCase(".properties")){
            xmlApropi(f);
        }else if(cambio.equalsIgnoreCase(".xml")){
            xmlAxml(f);
        }
    }
    
     //metodos de xml
    private void xmlAxml(File f){
        System.out.println("Dame el nombre del archivo en el que lo pegaremos");
        String nombre = sc.nextLine();
        System.out.println(nombre);
        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder procesador = fabrica.newDocumentBuilder();
            //lee xml original
            Document documento = procesador.parse(f);
            Element raiz = documento.getDocumentElement();
            
            //nuevo xml
            Document nuevo = procesador.newDocument();
            //raíz del nuevo xml
            nuevo.appendChild(raiz);
            
            //obtener elementos del original
            NodeList eOriginales = documento.getDocumentElement().getChildNodes();
            //Iterar sobre los originales
            for(int i=0; i<eOriginales.getLength(); i++){
                Node original = eOriginales.item(i);
                //nuevo elemento para el nuevo xml
                Element  eNuevo = nuevo.createElement(original.getNodeName());
                
                //copiar los atributos
                NamedNodeMap atributos = original.getAttributes();
                for (int j = 0; j < atributos.getLength(); j++) {
                    Node atributo = atributos.item(j);
                    eNuevo.setAttribute(atributo.getNodeName(), atributo.getNodeValue());
                }
                
                //Copiar el contenido del elemento original
                NodeList hijos = original.getChildNodes();
                for (int j = 0; j < 10; j++) {
                    Node hijo = hijos.item(j);
                    if(hijo.getNodeType()==Node.ELEMENT_NODE){
                        eNuevo.appendChild(nuevo.importNode(hijo, true));
                    }
                }
                //Adjuntar el elemento nuevo a la raíz del nuevo xml
                raiz.appendChild(eNuevo);
            }
            //el transfrom 
            TransformerFactory fabricaTrans = TransformerFactory.newInstance();
            Transformer  trans = fabricaTrans.newTransformer();
            DOMSource doms = new DOMSource(nuevo);
            StreamResult resultado = new StreamResult(new FileOutputStream (nombre));
            trans.transform(doms, resultado);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(ProyectoUD1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void xmlAdat (File f){// Método para convertir un archivo XML a un archivo .dat
        try {// Se solicita al usuario el nombre del nuevo archivo .dat
            System.out.println("Dame el nombre del nuevo fichero");
            String nom = sc.nextLine();
            
            // Se crea un objeto DocumentBuilder para analizar el XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(f);
            
            // Se obtiene el elemento "Alumno" del XML
            NodeList nodeList = document.getElementsByTagName("Alumno");
            Node node = nodeList.item(0);
            Element element = (Element) node;
            String nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
            int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
            
            // Se crea un objeto Alumno con los datos obtenidos
            Alumno al = new Alumno(id, nombre);

            // Escribir los objetos Java en un archivo .dat
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nom));
            outputStream.writeObject(al);
            outputStream.close();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ArchivoXml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ArchivoXml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoXml.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void xmlApropi(File f){// Método para convertir un archivo XML a archivos de propiedades (.properties)
        // Se crea un nuevo DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            // Se crea un nuevo DocumentBuilder a partir del DocumentBuilderFactory
            db = dbf.newDocumentBuilder();
            // Se parsea el archivo XML y se obtiene el Document correspondiente
            Document doc = db.parse(f);
            // Se normaliza la estructura del Document
            doc.getDocumentElement().normalize();
            // Se obtiene una lista de nodos con el tag "alumno"
            NodeList nl = doc.getElementsByTagName("alumno");
            // Se itera sobre la lista de nodos "alumno"
            for(int i=0; i<nl.getLength(); i++){
                Node node = nl.item(i);
                // Se verifica si el nodo es de tipo ELEMENT_NODE
                if(node.getNodeType()== Node.ELEMENT_NODE){
                    // Se convierte el nodo a un Element
                    Element elemento = (Element) node;
                    // Se obtiene el valor del atributo "id" como un entero
                    int id = Integer.parseInt(elemento.getAttribute("id"));
                    // Se obtiene el valor del elemento "nombre"
                    String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
                    // Se crea un objeto Alumno con los valores obtenidos
                    Alumno al = new Alumno(id, nombre);
                    // Se llama al método guardarPropi para guardar el objeto Alumno en un archivo de propiedades
                    guardarPropi(al);
                }
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ArchivoXml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ArchivoXml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoXml.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    private void guardarPropi(Alumno al) {// Método para guardar un objeto Alumno en un archivo de propiedades (.properties)
        // Se crea un objeto Properties para almacenar los datos del Alumno
        Properties propi = new Properties();
        propi.setProperty("id", String.valueOf(al.getId()));
        propi.setProperty("nombre", al.getNombre());
        
        // Se solicita al usuario el nombre del nuevo archivo .properties
        System.out.println("Dame el nombre del nuevo fichero");
        String nom = sc.nextLine();
        FileOutputStream fos = null;
        try {
            // Se abre un FileOutputStream para escribir en el archivo
            fos= new FileOutputStream(nom);
            // Se almacenan las propiedades en el archivo
            propi.store(fos, null);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoXml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoXml.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                // Se cierra el FileOutputStream
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(ArchivoXml.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
