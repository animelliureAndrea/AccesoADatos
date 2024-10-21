/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrea
 */
public class AccesoDAO {
    private ConexionMDB con;
    PreparedStatement sentencia;
    ResultSet rs;
    public Connection conex;

    public AccesoDAO() {
        con = new ConexionMDB();
        conex = con.getConection();
    }
    
    //aquí van las consultas a la base de datos
    //primero haremos una consulta que meta en un list los números de departamento
    public List<Integer> consultaCMB(){
        List<Integer> numDepart = new ArrayList<>();
        String query = "SELECT dept_no FROM depart";
        try {
            //mtemos la consulta en el prepared statement
            sentencia = conex.prepareStatement(query);
            rs = sentencia.executeQuery();//ejecuto la consulta
            while(rs.next()){//mientras el rs tenga resultados
                //llo recjo en una variable y lo meto a la lista
                int resultado = rs.getInt("dept_no");
                numDepart.add(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numDepart;
    }
    
    //despues una consulta que muestra todos los empleados que trabajan en el departamento que le pasen por parámetros
    //tambien los meteremos en una Lista
    public List<Emple> consultaEmples(int dept_no){
        List<Emple> emples = new ArrayList<>();
        String query = "SELECT * FROM emple WHERE dept_no="+dept_no;
        try {
            //mtemos la consulta en el prepared statement
            sentencia=conex.prepareStatement(query);
            rs = sentencia.executeQuery();
            //mientras el rs tenga resultados los almacenamos en variables, los metemos en un objeto emple y luego a la lista
            while(rs.next()){
                int emp_no = rs.getInt("dept_no");
                String apellido = rs.getString("apellido");
                String oficio = rs.getString("oficio");
                int dir = rs.getInt("dir");
                Date fecha_alt = rs.getDate("fecha_alt");
                double salario = rs.getDouble("salario");
                double comision = rs.getDouble("comision");
                int deptNo = rs.getInt("dept_no");
                Emple e = new Emple(emp_no, apellido, oficio, dir, fecha_alt, salario, comision, dept_no);
                emples.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emples;
    }
    
    public void desconectar() {
        try {
            conex.close();
            rs.close();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
