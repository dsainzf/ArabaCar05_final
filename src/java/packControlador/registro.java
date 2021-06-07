/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packControlador;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import utils.BD;

/**
 *
 * @author imano
 */
public class registro extends HttpServlet {
    
    private Connection conn;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        conn = BD.getConexion();
    }
    
    /*private boolean existeUsuario(String email) {
        boolean enc = false;
        try {
            String query = "SELECT * FROM usuarios WHERE Email = '" + email + "'";
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            if (rs.next()) {
                enc = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enc;
    }*/
    
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession s = request.getSession(true);
        
        s.setAttribute("existeCorreo", "");
        s.setAttribute("existeDNI", "");
        
        String nombre = (String) request.getParameter("nombre");
        String apellido = (String) request.getParameter("apellido");
        String correo = (String) request.getParameter("email");
        String contra = (String) request.getParameter("contrasenia");
        String DNI = (String) request.getParameter("dni");
        File Foto = null;
        //int movil = Integer.parseInt(request.getParameter("movil"));
        int edad = Integer.parseInt(request.getParameter("edad"));
        String coche = (String) request.getParameter("MarcaModelo");
        
        String cad;
        boolean existeCorreo = false;
        boolean existeDNI = false;
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM usuarios");
            while (rs.next() && !existeCorreo && !existeDNI) {
                cad = rs.getString("Email");
                cad = cad.trim();
                if(cad.compareTo(correo.trim()) == 0) {
                    existeCorreo = true;
                } else {
                    cad = rs.getString("DNI");
                    cad = cad.trim();
                    if (cad.compareTo(DNI.trim()) == 0) {
                        existeDNI = true;
                    }
                }
            }
            rs.close();
            
            if(existeCorreo){
                System.out.println("existecorreo");
                s.setAttribute("existeCorreo", "true");
            }
            else if(existeDNI){
                s.setAttribute("existeDNI", "true");
            }
            else {
                s.setAttribute("existo", "true");
                st.executeUpdate("INSERT INTO usuarios (`DNI`,`Email`,`Contraseña`,`Nombre`,`Apellido`,`Foto`,`movil`,`Marca_Modelo`) VALUES ( '"+DNI+"', '"+correo+"', '"+contra+"', '"+nombre+"', '"+apellido+"', '"+Foto+"', '"+636330751+"', '"+coche+"');");
                
            }
            st.close();
           
        } catch (SQLException ex1) {
            System.out.println("No lee de la tabla usuarios. " + ex1);
        }
        
        response.sendRedirect("login.jsp");
        
        //request.getRequestDispatcher("Index.jsp").forward(request, response);
        
        /*try (PrintWriter out = response.getWriter()) {
            TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet registro</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet registro at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
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
        processRequest(request, response);
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
        processRequest(request, response);
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
