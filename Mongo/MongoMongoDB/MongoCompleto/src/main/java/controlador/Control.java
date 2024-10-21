/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.DoctoresRepository;
import com.mongodb.client.MongoCollection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Doctores;
import vista.Vista;

/**
 *
 * @author Andrea
 */
public class Control implements ActionListener{
    vista.Vista v;
    DoctoresRepository dr;
    MongoCollection<Doctores> doc;

    public Control() {
        v = new Vista();
        dr = new DoctoresRepository(doc);
    }
    
    public Doctores recoger(){
        //recoger campos de la vista
        String nombre = v.txtNom.toString();
        String apellido = v.txtApellido.toString();
        int dep = Integer.parseInt(v.txtDep.toString());
        float sal = Float.parseFloat(v.txtSal.toString());
        
        Doctores d = new Doctores(nombre, apellido, dep, sal);
        return d;
    }
    
    public void insertar(){
        Doctores d = recoger();
        //insertar
        dr.insertaUno(d);
    }
    
    public void consulta(){
        dr.todos(v.txtMuestra);
    }
    
    public void insertaDoc(){
        Doctores d = recoger();
        dr.insertaDocu(d);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(v.btnConsulta)){
            consulta();
        }else if(e.getSource().equals(v.btnInsertDoc)){
            insertaDoc();
        }else if(e.getSource().equals(v.btnInserta)){
            insertar();
        }
        
    }
}
