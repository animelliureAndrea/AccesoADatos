/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import java.awt.TextArea;
import javax.swing.JTextArea;
import modelo.Doctores;
import org.bson.Document;

/**
 *- insertar un nuevo doctor mediante objeto

- consultar todos los doctores y guardarlos en objetos

- insertar un doctor con Document
 * @author Andrea
 */
public class DoctoresRepository {
    MongoCollection<Doctores> doc;
    //private Conexion con;

    public DoctoresRepository(MongoCollection<Doctores> doc) {
        this.doc = doc;
    }
    
    public void insertaUno(Doctores d){
        InsertOneResult inserta1 = doc.insertOne(d);
        System.out.println("Persona insertada"+inserta1.getInsertedId());
    }
    
    public void todos(JTextArea txtMuestra){
        FindIterable<Doctores> todoss=doc.find();
        todoss.forEach(doctors -> txtMuestra.append(doctors.toString()+"\n"));
    }
    
    public void insertaDocu(Doctores d){
        Document doc = new Document("nombre", d.getNombre())
                .append("apellido", d.getApellido())
                .append("salario", d.getSalario())
                .append("departamento", d.getDep());
        //con.getCollection().insertOne(doc);
    }
}
