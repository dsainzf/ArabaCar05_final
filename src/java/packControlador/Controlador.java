/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packControlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author imano
 */
@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {
    
    private Connection con;
    private Statement set;
    private ResultSet rs;
    private ResultSet rs1;
    
    String cad, cad1, cad2,cad3, cad4, cad5, cad6;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Viajes Disponibles</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Viajes disponibles en " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            String contrasenia = (String) request.getParameter("contrasenia");
        
            String nombre = (String) request.getParameter("nombre");
            String apellido = (String) request.getParameter("apellido");
            String dni = (String) request.getParameter("dni");
            String movil = (String) request.getParameter("movil");
            String email = (String) request.getParameter("email");
            String foto = (String) request.getParameter("foto");
            String marca_modelo = (String) request.getParameter("marcaModelo");
            
            String origen = (String) request.getParameter("origen");
            
            boolean logueado = false;
            request.getSession().setAttribute("logueado",false);
            
            //BUSCAR VIAJE
            request.getParameter("origen");
            request.getParameter("destino");
            
            rs = set.executeQuery("SELECT * FROM viaje");
            
            while (rs.next()) {
                cad = rs.getString("Origen");
                cad = cad.trim();
                cad1 = rs.getString("destino");
                cad1 = cad1.trim();
                //cad2 = rs.getDate(Fecha_Salida);
                //cad2 = cad2.trim();
                
                if (cad.equals(origen)) {
                    
                }
            }
            //REALIZAR REGISTRO
            if ( request.getParameter("registrarse") != null  ) {
            
            System.out.println("Registrando");
            
            boolean existe = false;
            try {
                set = con.createStatement();
                rs = set.executeQuery("SELECT * FROM usuarios");
                while (rs.next()) {
                    cad = rs.getString("email");
                    cad = cad.trim();
                    if (cad.compareTo(email.trim()) == 0) {
                        existe = true;
                    }
                }
                rs.close();
                set.close();
            } catch (SQLException ex1) {
                System.out.println("No lee de la tabla ususarios. " + ex1);
            }
            
            System.out.println("Existe " + existe );
            
            try {
                
                set = con.createStatement();
                if(!existe)
                {
                    
                    String sql = "INSERT INTO usuarios "
                            + "(DNI, Email, Contraseña, Nombre, Apellido, Foto, Movil, Marca_Modelo) VALUES ('" + dni + "', '" + email + "', '" + contrasenia + "', '" + nombre + "', '" + apellido + "', '" + foto + "', '" + movil + "', '" + marca_modelo + "')";
                    
                    System.out.println(sql);

                    set.executeUpdate( sql );
                    //response.sendRedirect("Login");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    
                }
                else
                {
                    System.out.println("El usuario ya existe");
                    JOptionPane.showMessageDialog(null, "El usuario ya existe" );
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                
            } catch (SQLException ex2) {
                System.out.println("No te has registrado correctamente." + ex2);
            }
        }
            //REALIZAR LOGIN 
            else if (request.getParameter("btnLogin") != null ){
                System.out.println("btnLogin");
                
                try {
                    set = con.createStatement();
                    
                    System.out.println("Se mete a login");
                    
                    /*if(email.trim().equalsIgnoreCase("rs@gmail.com")) {
                        rs1 = set.executeQuery("SELECT * FROM ususarios");
                        
                        while (rs1.next()) {
                            cad = rs1.getString("email");
                            cad = cad.trim();
                            cad1 = rs1.getString("contrasenia");
                            cad1 = cad1.trim();
                            
                            if (cad.equals(email)) {
                                System.out.println("email correcto");
                                if(cad1.equals(contrasenia)) {
                                    JOptionPane.showMessageDialog(null, "El usuario se ha logueado correctamente" );
                              
                                request.getRequestDispatcher("Index.jsp").forward(request, response);
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "El usuario se no ha logueado correctamente" );
                                request.getRequestDispatcher("login.jsp").forward(request, response);
                                }
                            }
                        }
                    }
                    else {*/
                        rs = set.executeQuery("SELECT * FROM usuarios");
                        
                        while (rs.next()) {
                            cad = rs.getString("email");
                            cad = cad.trim();
                            cad1 = rs.getString("contraseña");
                            cad1 = cad1.trim();
                            cad2 = rs.getString("nombre");
                            cad2= cad2.trim();
                            cad3 = rs.getString("foto");
                            cad3= cad3.trim();
                        
                            if (cad.equals(email)) {
                                System.out.println("email correcto");
                                if(cad1.equals(contrasenia)) {
                                    JOptionPane.showMessageDialog(null, "El usuario se ha logueado correctamente" );
                            
                                logueado = true;
                                request.getSession().setAttribute("logueado",true);
                                request.getSession().setAttribute("nombre",cad2);
                                request.getSession().setAttribute("imagen",cad3);
                                request.getSession().setAttribute("email",cad);
                                request.getRequestDispatcher("index.jsp").forward(request, response);
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta" );
                                    request.getRequestDispatcher("login.jsp").forward(request, response);
                            }
                        }
                            else{
                                //JOptionPane.showMessageDialog(null, "El usuario se ha logueado incorrectamente" );
                            System.out.println("El usuario se ha logueado incorrectamente");
                            }    
                        }
                    /*}*/
                    rs.close();
                    rs1.close();
                    set.close();
                } catch (SQLException ex1) {
                System.out.println("No lee de la tabla usuarios. " + ex1);
            }      
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
