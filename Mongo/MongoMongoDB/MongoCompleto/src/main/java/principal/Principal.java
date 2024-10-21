/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import controlador.Control;
import vista.Vista;

/**
 *
 * @author Andrea
 */
public class Principal {
    
    public static void main(String[] args) {
        Vista v = new Vista();
        Control c = new Control();
        
        v.setVisible(true);
    }
}
