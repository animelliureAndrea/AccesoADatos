/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mongodb;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;
import modelo.Empleado;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

/**
 *
 * @author Diurno
 */
public class MongoDB {

    public static void main(String[] args) {
        //MongoConector
        String uri ="mongodb://localhost:27017";
        PojoCodecProvider pojo = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry coder = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), CodecRegistries.fromProviders(pojo));
        
        try(MongoClient cliente = MongoClients.create(uri)){
            System.out.println("nos conectamos");
            MongoDatabase mdb = cliente.getDatabase("bd_empleados").withCodecRegistry(coder);
            System.out.println(mdb.getCollection("empleados").getNamespace());
        
            //obtener una colección
            MongoCollection<Empleado> empColect = mdb.getCollection("empleados", Empleado.class);
            
            //insertar datos en una colección
            Empleado e1 = new Empleado("Pepita", 23);
            InsertOneResult insertUno = empColect.insertOne(e1);
            System.out.println(insertUno.getInsertedId());
            
            //insertar varios
            List<Empleado> emples = new ArrayList<>();
            Empleado e2 = new Empleado("Juliana", 57);
            Empleado e3 = new Empleado("Dario", 30);
            Empleado e4 = new Empleado("Amat", 44);
            emples.add(e4);
            emples.add(e3);
            emples.add(e2);
            InsertManyResult muchos = empColect.insertMany(emples);
            System.out.println(muchos.getInsertedIds());
            
            //consulta
            FindIterable<Empleado> encuentra = empColect.find();
            encuentra.forEach(emple -> System.out.println(emple));
            
            //actualizar
            Bson condicionWhere = Filters.eq("nombre", "Pepita");
            Bson condicion = Updates.set("dep", 23);
            UpdateResult updateOne = empColect.updateOne(condicionWhere, condicion);
            System.out.println(updateOne.getMatchedCount());
            
            //consulta por filtros
            
            //crear bd
            MongoDatabase bdNueva = cliente.getDatabase("bd_ej1").withCodecRegistry(coder);
            MongoCollection<Empleado> empColection = bdNueva.getCollection("personas", Empleado.class);
                    
         }
    }
}
