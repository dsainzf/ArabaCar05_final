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
import java.util.ArrayList;
import java.util.Calendar;
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
public class BuscarViaje extends HttpServlet {

    private Connection conn;
    private Statement set;
    private ResultSet rs;
    private ArrayList resultado = new ArrayList<>();
    private boolean hayViajes;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        conn = BD.getConexion();
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
        /*try (PrintWriter out = response.getWriter()) {
             TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BuscarViaje</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuscarViaje at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        processRequest(req, res);
        
        HttpSession s = req.getSession(true);
        
        s.setAttribute("sitiosIguales", "");
        s.setAttribute("fechaAnterior", "");
        
        String origen = (String) req.getParameter("origen");
        String destino = (String) req.getParameter("destino");
        String fecha = (String) req.getParameter("fecha");
        
        boolean sitiosDistintos = (!origen.equals(destino));
        
        Calendar c = Calendar.getInstance();
        int diaHoy = c.get(Calendar.DATE);
        int mesHoy = c.get(Calendar.MONTH);
        int añoHoy = c.get(Calendar.YEAR);
        int diasHoy = (añoHoy-1)*365 + mesHoy*30 + diaHoy;
        
        String[] fechaSeparada = fecha.split("-");
        int año = Integer.parseInt(fechaSeparada[0]); 
        int mes = Integer.parseInt(fechaSeparada[1]); 
        int dia = Integer.parseInt(fechaSeparada[2]); 
        int diasBusqueda = (año-1)*365 + (mes-1)*30 + dia;
        
        if(diasHoy <= diasBusqueda && sitiosDistintos) {
            
            try {
                set = conn.createStatement();
                rs = set.executeQuery("SELECT * FROM viaje Where Origen='" + origen + "' and Destino='" + destino + "' and Fecha_Salida >='"+ fecha + "' ORDER BY Fecha_Salida, Hora_Salida");
                if(rs.next()) {
                    hayViajes = true;
                    resultado = new ArrayList<>();
                    do {
                        String[] viaje = new String[6];
                        int codigo = rs.getInt("IdViaje"); 
                        viaje[0] = Integer.toString(codigo);
                        viaje[1] = origen;
                        viaje[2] = destino;
                        String fecha2 = rs.getString("Fecha_Salida");
                        viaje[3] = fecha2;
                        String hora = rs.getString("Hora_Salida");
                        viaje[4] = hora;
                        float precio = rs.getFloat("Precio");
                        viaje[5] = Float.toString(precio);

                        resultado.add(viaje);
                        
                    } while(rs.next());
                    
                    s.setAttribute("resultadoViajes", resultado);
                }
                else{
                    hayViajes = false;
                    resultado = new ArrayList<>();
                    s.setAttribute("resultadoViajes", resultado);
                }
                s.setAttribute("hayViajes", hayViajes);
                rs.close();
                set.close();
            } catch (SQLException ex1) {
            System.out.println("No lee de la tabla Viajes. " + ex1);
        }
        } else {
            s.removeAttribute("resultadoViajes");
            if(!sitiosDistintos) {
                s.setAttribute("sitiosIguales", "true");
            } else {
                s.setAttribute("fechaAterior", "true");
            }
        } 
        res.sendRedirect("Index.jsp");
    }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold> */


