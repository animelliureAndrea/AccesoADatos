/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import java.util.List;
import modelo.Persona;
import org.bson.conversions.Bson;

/**
 *Insertar una persona
Insertar varias personas
Actualizar una persona
Actualizar varias personas
Consultar todas las personas
Consultas personas por filtro
 * @author Andrea
 */
public class Metodos {
    
    MongoCollection<Persona> personas;

    public Metodos(MongoCollection<Persona> personas) {
        this.personas = personas;
    }
        
    public void insertaUna(Persona p){
        InsertOneResult insertaUna = personas.insertOne(p);
        System.out.println("Persona insertada: "+insertaUna.getInsertedId());
    }
    
    public void insertaMuchas(List<Persona> p){
        InsertManyResult insertaMas = personas.insertMany(p);
        System.out.println("Personas insertadas: "+insertaMas.getInsertedIds());
    }
    
    public void actualizaUna(Bson filtro, Bson actualiza){
        UpdateResult actualiza1 = personas.updateOne(filtro, actualiza);
        System.out.println("Actualizadas: "+actualiza1.getMatchedCount());
    }
    
    public void actualizaMas(Bson filtro, Bson actualiza){
        UpdateResult actualizaMas = personas.updateMany(filtro, actualiza);
        System.out.println("Actualizadas: "+actualizaMas.getMatchedCount());
    }
    
    public void encuentraToo(){
        FindIterable<Persona> encuentra = personas.find();
        System.out.println("Personas encontradas: ");
        encuentra.forEach(persona -> System.out.println(persona));
    }
    
    public void encuentraMas(Bson filtro){
        FindIterable<Persona> encuentra = personas.find(filtro);
        System.out.println("Personas encontradas: ");
        encuentra.forEach(persona -> System.out.println(persona));
    }
}
