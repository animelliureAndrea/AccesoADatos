/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoud1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Andrea
 */
public class XMLaTXT extends DefaultHandler{
    private StringBuilder cargar;
    private BufferedWriter escribir;

    public XMLaTXT(File f) {
        cargar=new StringBuilder();
        try {
            escribir = new BufferedWriter(new FileWriter(f));
        } catch (IOException ex) {
            Logger.getLogger(XMLaTXT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
         // Método invocado al encontrar el inicio de un elemento en el XML
        // Escribe el nombre del elemento en el archivo de texto
         escribeFile("Elemento: "+qName);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
            // Método invocado al encontrar el final de un elemento en el XML    
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // Método invocado al encontrar los caracteres dentro de un elemento en el XML
        // Extrae y escribe el valor de los elementos en el archivo de texto
        String  value = new String(ch, start, length).trim();
        if(!value.isEmpty()){
            escribeFile("Valor: "+value);
        }
    }
    
    private void escribeFile(String contenido) {
        try {
            // Método para escribir en el archivo de texto
            escribir.write(contenido);
            escribir.newLine();
        } catch (IOException ex) {
            Logger.getLogger(XMLaTXT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarEscritura(){
        // Método para cerrar el escritor cuando se complete el proceso de lectura y escritura
        if(escribir!=null){
            try {
                escribir.close();
            } catch (IOException ex) {
                Logger.getLogger(XMLaTXT.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void parseXML(String salida){
        // Método para iniciar el proceso de análisis del XML
        // Configuración del parser SAX y análisis del XML
        
        try {
            SAXParserFactory fabrica = SAXParserFactory.newInstance();
            SAXParser sax= fabrica.newSAXParser();
            sax.parse(new File(salida), this);// Se pasa 'this' como el manejador de eventos
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLaTXT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLaTXT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLaTXT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
