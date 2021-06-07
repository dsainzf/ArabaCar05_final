/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packControlador;

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
    
    private boolean existeUsuario(String email) {
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
    }
    
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
        
        String email = request.getParameter("email");
        
        if(existeUsuario(email)) {
            request.setAttribute("Aviso", "Ya existe un usuario con estos mismos datos");
            
        } else {
            
            try {
                
                String query = "INSERT INTO usuarios (`DNI`,`Email`,`Contraseña`,`Nombre`,`Apellido`,`Foto`,`movil`,`Marca_Modelo`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);";
                
                pst = conn.prepareStatement(query);
                
                String dni = request.getParameter("dni");
                pst.setString(1, dni);
                
                pst.setString(2, email);
                
                String pwd = request.getParameter("contrasenia");
                pst.setString(3, pwd);
                
                String nombre = request.getParameter("nombre");
                pst.setString(4, nombre);
                
                String apellido = request.getParameter("apellido");
                pst.setString(5, apellido);
                
                String foto = request.getParameter("foto");
                pst.setString(6, foto);
                
                Part filePart = request.getPart("foto");
                InputStream inputStream = filePart.getInputStream();
                pst.setBlob(6, inputStream);
                
                String Marca_Modelo = request.getParameter("Marca_Modelo");
                pst.setString(8, Marca_Modelo);
                
                int num = pst.executeUpdate();
                
                if (num != 0) {
                    request.setAttribute("AvisoUsuario", "Usuario agregado correctamente");
                }
                
            } catch (SQLException ex) {
                request.setAttribute("Aviso", "Error inesperado");
                Logger.getLogger(registro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        request.getRequestDispatcher("registro.jsp").forward(request, response);
        
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
