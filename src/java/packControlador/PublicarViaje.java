/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packControlador;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.Calendar;
import utils.BD;

/**
 *
 * @author imano
 */
public class PublicarViaje extends HttpServlet {

    private Connection con;
    private Statement set;
    private ResultSet rs;
    
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
            out.println("<title>Servlet PublicarViaje</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PublicarViaje at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
    }

    public void init(ServletConfig cfg) throws ServletException {
        con = BD.getConexion();
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
        
        HttpSession s = request.getSession(true);
        
        s.setAttribute("existeViaje", "");
        
        String origen = request.getParameter("origen").trim();
        String destino = request.getParameter("destino").trim();
        String fecha = request.getParameter("fecha").trim();
        String hora = (request.getParameter("hora")+":00").trim();
        String precio = request.getParameter("precio").trim();
        
        String DNI = (String) s.getAttribute("DNIUsuario");
        
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
        int diasPublicacion = (año-1)*365 + (mes-1)*30 + dia;
        
        int horasHoy = c.get(Calendar.HOUR);
        int minutosHoy = c.get(Calendar.MINUTE);
        int tiempoHoy = horasHoy*60 + minutosHoy;
        
        String[] horaSeparada = hora.split(":");
        int horas = Integer.parseInt(horaSeparada[0]); 
        int minutos = Integer.parseInt(horaSeparada[1]); 
        int tiempoPublicacion = horas*60 + minutos;
        
        boolean horaValida = tiempoHoy < tiempoPublicacion;
        
        if((diasHoy < diasPublicacion && sitiosDistintos) || (diasPublicacion == diasHoy && horaValida && sitiosDistintos) ){
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM viaje WHERE DNI='"+DNI+"'");
            boolean existeViaje=false;
            while (rs.next() && !existeViaje) {
                String origen2 = rs.getString("Origen").trim();
                String destino2 = rs.getString("Destino").trim();
                String fecha2 = rs.getString("Fecha_Salida").trim();
                String hora2 = rs.getString("Hora_Salida").trim();
                if (origen.compareTo(origen2) == 0 &&
                    destino.compareTo(destino2) == 0 &&
                    fecha.compareTo(fecha2) == 0 &&
                    hora.compareTo(hora2) == 0) {
                    s.setAttribute("existeViaje", "true");
                    existeViaje = true;
                }
            }
            if(!existeViaje){
                s.setAttribute("exito", "true");
                set.executeUpdate("INSERT INTO viaje(IdViaje, DNI, Origen, Fecha_Salida, Hora_Salida, Precio, Destino)"
                        + "values('6','"+DNI+"','"+origen+"','"+fecha+"','"+hora+"','"+precio+"','"+destino+"')");
            }
            
            rs.close();
            set.close();
            
        } catch (SQLException ex1) {
            System.out.println("No lee de la tabla Viajes. " + ex1);
        } 
        
    }
    else{
            
        if(!sitiosDistintos){
            s.setAttribute("sitiosIguales", "true");
        }
        else if(!horaValida){
            s.setAttribute("horaNoValida", "true");
        }
        else{
            s.setAttribute("fechaAnterior", "true");
        }
    }
        
    response.sendRedirect("PublicarViaje.jsp");
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
