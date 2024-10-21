/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrea
 */
public class ConexionMDB {
    String BD = "hibernate";
    String dir = "jdbc:mysql://localhost/"+BD;
    String user = "root";
    String paswd = "root";
    Connection c = null;
    
    public Connection getConection(){
        if(c==null){
            try {
                c=DriverManager.getConnection(dir,user,paswd);
            } catch (SQLException ex) {
                Logger.getLogger(ConexionMDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return c;
    }
    
    public void desconectar(){
        if(c!=null){
            try {
                c.close();
                c=null;
            } catch (SQLException ex) {
                Logger.getLogger(ConexionMDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
