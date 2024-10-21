/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import bd.Metodos;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.List;
import modelo.Persona;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

/**
 *
 * @author Andrea
 */
public class Principal {
    public static void main(String[] args) {
        String uri = "mongodb://localhost:27017";
        PojoCodecProvider pojo = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry codec = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(pojo));
        
        try(MongoClient cliente = MongoClients.create(uri)){
            System.out.println("Nos conectamos");
            
            //Obtener o crear una BD
            MongoDatabase bd = cliente.getDatabase("bd_personas").withCodecRegistry(codec);
            System.out.println("Entramos a la colección: "+bd.getCollection("personas").getNamespace()); 
            
            //Obtener una coleccion
            MongoCollection<Persona> personasCol = bd.getCollection("personas", Persona.class);
            
            //Instanciar el repositorio
            Metodos rep = new Metodos(personasCol);
            
            //Insertar una persona
            Persona p1 = new Persona("Merida", "Ona", 123, 456, 12, true);
            rep.insertaUna(p1);
            
            //Insertar varias personas
            List<Persona> personas = new ArrayList<>();
            Persona p2 = new Persona("Kenzo", "Perfecto", 789, 1011, 11, true);
            Persona p3 = new Persona("Ethan", "Algo", 1213, 1415, 0, false);
            personas.add(p3);
            personas.add(p2);
            rep.insertaMuchas(personas);
            
            //Actualizar una persona
            Bson filtros = Filters.eq("numSegSocial", 1213);
            Bson cambios = Updates.set("telefono", 2627);
            rep.actualizaUna(filtros, cambios);
            
            //Actualizar varias personas
            Bson filtros2 = Filters.eq("apellido", "Algo");
            Bson cambios2 = Updates.set("apellido", "Feliz");
            rep.actualizaMas(filtros2, cambios2);
            
            //Consultar personas
            rep.encuentraToo();
            
            //Consultar con condiciones
            Bson filtros3 = Filters.eq("nombre", "Kenzo");
            rep.encuentraMas(filtros3);
        }
    }
}
