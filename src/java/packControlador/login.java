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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.BD;

/**
 *
 * @author imano
 */
public class login extends HttpServlet {
    
    private Connection conn;
    
    private int existeUsuario(String email) {
        
        int cont = 0;
        
        try {
            
            Statement stmt = conn.createStatement();
            
            //NO publica viaje si ya existe ese mismo viaje
            String query = "select * from ususarios where Email = '" + email + "';";
            
            ResultSet rs2 = stmt.executeQuery(query);
            
            System.out.println(query);
            
            if (rs2.next()) {
                cont = 1;
                System.out.println("ENCONTRADO TRUE");
            }
        } catch (SQLException e) {
            cont = -1;
        }
        return cont;
        
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
        
        conn = BD.getConexion();
        Statement st; //sentencia SQL
        ResultSet rs; //el resultado de la sentencia
        
        String correo = request.getParameter("email");
        String contra = request.getParameter("contrasenia");
        
        System.out.println("El correo del formulario: " + correo);
        System.out.println("la contraseña del formulario: " + contra);
        
        if (existeUsuario(correo) == 1){
            
            try {
                st = conn.createStatement();
                rs = st.executeQuery("select Email, Contraseña, Marca_Modelo from ususarios");
                
                while (rs.next()){
                    
                    String email = rs.getString("email");
                    String password = rs.getString("contrasenia");
                    
                    if (correo.equals(email)) {
                        
                        if(contra.equals(password)) {
                            
                            System.out.println("Dentro de la BD");
                            
                            HttpSession s = request.getSession();
                            
                            s.setAttribute("email", email);
                            s.setAttribute("contrasenia", password);
                            
                            if(!rs.getString("MarcaModelo").equals("")){
                                s.setAttribute("MarcaModelo", rs.getString("MarcaModelo"));
                                System.out.println("hola:" + rs.getString("MarcaModelo") + ":");
                            }
                            
                            response.sendRedirect("Index.jsp");
                            break;
                        } else {
                            //El email no existe
                            request.setAttribute("Aviso", "La clave es incorrecta");
                            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                            rd.include(request, response);
                            break;
                        }
                }
                }
                
                rs.close();
                st.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } else {
            //El email no existe
            request.setAttribute("Aviso", "El email no esta registrado");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.include(request, response);
        }
        
        /*try (PrintWriter out = response.getWriter()) {
             TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet login at " + request.getContextPath() + "</h1>");
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
