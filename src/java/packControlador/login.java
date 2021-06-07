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
import javax.servlet.ServletConfig;
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
    private Statement st, stmt; //sentencia SQL
    private ResultSet rs, rs2; //el resultado de la sentencia

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        conn = BD.getConexion();
    }

    private int existeUsuario(String email) {

        int cont = 0;

        try {

            stmt = conn.createStatement();

            //NO publica viaje si ya existe ese mismo viaje
            String query = "select * from ususarios where Email = '" + email + "';";

            rs2 = stmt.executeQuery(query);

            System.out.println(query);

            if (rs2.next()) {
                cont = 1;
                System.out.println("ENCONTRADO TRUE");
            } else {
                System.out.println(" NO ENCONTRADO");
            }
        } catch (SQLException e) {
            System.out.println("ERROR");
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

        String correo = request.getParameter("email");
        String contra = request.getParameter("contrasenia");

        System.out.println("El correo del formulario: " + correo);
        System.out.println("la contraseña del formulario: " + contra);

        //if (existeUsuario(correo) == 1){
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select Email, Contraseña, Marca_Modelo from usuarios where Email = '" + correo + "';");

            if (rs.next()) {

                String email = rs.getString("Email");
                String password = rs.getString("Contraseña");

                if (correo.equals(email)) {

                    if (contra.equals(password)) {

                        System.out.println("Dentro de la BD");

                        HttpSession s = request.getSession();

                        s.setAttribute("email", email);
                        s.setAttribute("contrasenia", password);

                        if (!rs.getString("Marca_Modelo").equals("") && !rs.getString("Marca_Modelo").equals("null")) {
                            s.setAttribute("Marca_Modelo", rs.getString("Marca_Modelo"));
                            System.out.println("El coche:" + rs.getString("Marca_Modelo"));
                            response.sendRedirect("ConductorLogueado.jsp");
                        } else {
                            response.sendRedirect("PasajeroLogueado.jsp");
                        }
                        
                    } else {
                        
                        request.setAttribute("Aviso", "La clave es incorrecta");
                        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                        rd.forward(request, response);

                    }
                } else {
                    request.setAttribute("Aviso", "El email es incorrecta");
                    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                    rd.forward(request, response);
                } 
                
        } else {
            //El email no existe
            request.setAttribute("Aviso", "El email no esta registrado");
            RequestDispatcher rd = request.getRequestDispatcher("registro.jsp");
            rd.forward(request, response);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
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
