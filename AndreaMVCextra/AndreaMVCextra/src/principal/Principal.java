/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import cotrolodor.Control;
import vista.Vista;

/**
 *
 * @author Andrea
 */
public class Principal {
    public static void main(String[] args) {
        //llamo a la vista, llamo al controlador, le paso todo lo necesario y muestro la vista
        vista.Vista v = new Vista();
        cotrolodor.Control control = new Control(v.cmbNumDepart, v.lstEmples);
        v.setVisible(true);
    }
}
