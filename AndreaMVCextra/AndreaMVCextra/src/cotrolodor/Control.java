/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cotrolodor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import modelo.AccesoDAO;
import modelo.Lista;

/**
 *
 * @author Andrea
 */
public class Control {
    //preparo un combo box y un table para hacer la inserción de los datos en ellos y llamo a la clase con las consultas y la clase con la lista
    private JComboBox<String> cmb;
    private JList<String> lst;
    
    private modelo.AccesoDAO consultas;
    private modelo.Lista lista;

    public Control(JComboBox<String> cmb, JList<String> lst) {//un constructor con los elementos que me pasará la principal, la lista y el combo box
        this.cmb = cmb;
        this.lst = lst;
        
        consultas = new AccesoDAO();
        lista = new Lista();
        //llamo a los métodos
        llenarCmb();
        // Llenar la tabla inicialmente
        llenarTabla();
        
         // Agregar un listener de selección al JComboBox
        cmb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llenar la tabla cuando se seleccione un nuevo elemento
                llenarTabla();
            }
        });
    }

    
    public void llenarCmb(){
        //vacío el cmb
        cmb.removeAllItems();
        //saco el tamaño de la lista
        int tamano = lista.deps.size();
        for(int i =0; i<tamano; i++){//itero en todos los elementos de la lista de depart para añadirlos uno a uno al combo box
            cmb.addItem(lista.muestraDepart(i));
        }
    }
    
    public void llenarTabla(){
        // Crear un modelo para la lista
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        // Vaciar el modelo de la lista
        modeloLista.clear();
        // Obtener el departamento seleccionado como String
        String departamentoSeleccionado = (String) cmb.getSelectedItem();
         // Verificar si el valor seleccionado no es nulo
        if (departamentoSeleccionado != null) {
            // Convertir el departamento seleccionado a int
            int numSelec = Integer.parseInt(departamentoSeleccionado);
            int tamano = lista.llenaEmple(numSelec);
            //itero en todos los elementos de la lista de emples para añadirlos uno a uno al modelo de la lista
            for(int i=0; i<tamano; i++){
                modeloLista.addElement(lista.muestraEmple(i));
            }
        } else {
            System.out.println("No se ha seleccionado ningún departamento.");
        }
        // Establecer el modelo actualizado en la lista
        lst.setModel(modeloLista);
    }
}
