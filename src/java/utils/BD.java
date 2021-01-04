/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MCO
 */
public class BD {

    // Referencia a un objeto de la interface java.sql.Connection 
    private static Connection conn;
    
    /*public void init(ServletConfig cfg) throws ServletException { 
        String URL="jdbc:mysql://localhost/arabacar05?serverTimezone=UTC"; 
        String userName="root"; 
        String password="root"; 
        try { 
            Class.forName("com.mysql.jdbc.Driver").newInstance(); 
            conn = DriverManager.getConnection(URL, userName, password);
            System.out.println("Se ha conectado"); 
        } 
        catch(ClassNotFoundException ex1) {
            System.out.println("No se ha conectado: "+ex1); 
        } catch (IllegalAccessException ex2) {
            System.out.println("No se ha conectado: "+ex2);
        } catch (InstantiationException ex3) {
            System.out.println("No se ha conectado: "+ex3);
        } catch (SQLException ex4) {
            System.out.println("No se ha conectado: "+ex4);
        } 
    }*/

    public static Connection getConexion() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/arabacar05", "root", "root");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/arabacar05?serverTimezone=UTC", "root", "root");
                System.out.println("Se ha conectado.");
            } catch (ClassNotFoundException ex1) {
                System.out.println("No se ha conectado: " + ex1);
            } catch (SQLException ex2) {
                System.out.println("No se ha conectado:" + ex2);
            }
        }
        return conn;  
    }
         
    public static void destroy() {
        System.out.println("Cerrando conexion...");
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("No se pudo cerrar la conexion");
            System.out.println(ex.getMessage());
        }
    }
}
