<%-- 
    Document   : ReservasPasajero
    Created on : 23-jun-2021, 23:23:25
    Author     : imano
--%>

<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="utils.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Mis reservas</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/logueado.css">
        <link rel="icon" type="image/png" href="resources/ArabaCarLogo.png"/>
        <script src="js/cerrarSesion.js"></script>
        <script src="js/mostrarViajesPublicados.js"></script>
    </head>
    <body>
        <div id="contenedor">
            <a title="logo" href="LogueadoPasajero.jsp"><img id="logo" src="resources/ArabaCarLogo.png" alt="logo" height="53"/></a>
            <nav id="navegador">
                <p id="saludo">Hola, <%=session.getAttribute("Nombre")%> </p>
              <ul>
                <li><a id="cerrarSesion" onclick="cerrarSesion()">Cerrar sesión</a></li>
                <li><a class="active" href="ReservasPasajero.jsp">Ver mis reservas</a></li>
                <li><a href="LogueadoPasajero.jsp" id="arabacarPasajero">ArabaCar</a></li>
              </ul>  
            </nav>
            <br>
            <br>
            <br>
            <br>
            <script type="text/javascript"> 
                    function cerrarSesion(){
                        <%
                        session.removeAttribute("DNI");
                        session.removeAttribute("Nombre");
                        %>
                        if(confirm("Cerrar sesión")){
                            window.location="Index.jsp";
                        }
                    }
                </script>
            <section id ="viajesrealizados">
                <h5>Reservas realizadas</h5>
            <%!
                private Connection con;
                private Statement set;
                private ResultSet rs;
                
                public void jspInit() {
                    con = BD.getConexion();
                };
            %>
            <%
            try {   
                    int codigo;
                    String origen;
                    String destino;
                    String fecha;
                    String hora;
                    float precio;
                    String fechaReserva;
                    String horaReserva;
                    String DNI = (String) session.getAttribute("DNIUsuario");
                    Calendar c = Calendar.getInstance();
                    int diaHoy = c.get(Calendar.DATE);
                    int mesHoy = c.get(Calendar.MONTH);
                    int añoHoy = c.get(Calendar.YEAR);
                    String fechaHoy = Integer.toString(añoHoy)+"-"+Integer.toString(mesHoy+1)+"-"+Integer.toString(diaHoy);
                    
                    int horasHoy = c.get(Calendar.HOUR_OF_DAY);
                    int minutosHoy = c.get(Calendar.MINUTE);
                    String horaHoy = Integer.toString(horasHoy)+":"+Integer.toString(minutosHoy)+":00";
                    System.out.println(horaHoy);
                    set = con.createStatement();
                    rs = set.executeQuery("SELECT * FROM reservaviaje NATURAL JOIN viaje WHERE DNI='"+DNI+"' and (Fecha_Salida<'"+fechaHoy+"' "
                            + "or (Fecha_Salida='"+fechaHoy+"' and Hora_Salida<='"+horaHoy+"')) ORDER BY Fecha_Salida, Hora_Salida");
                    while (rs.next()) {
                        codigo = rs.getInt("IdViaje");
                        origen = rs.getString("Origen");
                        destino = rs.getString("Destino");
                        fecha = rs.getString("fechaSalida");
                        hora = rs.getString("horaSalida");
                        precio = rs.getFloat("precio");
                        fechaReserva = rs.getString("fechaReserva");
                        horaReserva = rs.getString("horaReserva");
            %>                         
            <div id='listaviaje'>

                    <b>ID: <%=codigo%></b><br>
                    Origen: <%=origen%> - Destino: <%=destino%><br>
                    Fecha: <%=fecha%> - Hora: <%=hora%> - Precio: <%=precio%>€<br><br>
                    <b>DATOS DE LA RESERVA</b><br>
                    Fecha: <%=fechaReserva%> - Hora: <%=horaReserva%><br>
                    <input type="submit" value="Detalles" id="botonDetallesRealizados">
            </div>
                <%
                        }
                        rs.close();
                        set.close();
                    } catch (Exception ex) {
                        System.out.println("Error en acceso a Reserva_Viajes" + ex);
                    }
                %> 
            </section>
            <section id ="viajesporrealizar">
                <h5>Reservas pendientes</h5>
            <%
            try {   
                    int codigo;
                    String origen;
                    String destino;
                    String fecha;
                    String hora;
                    float precio;
                    String fechaReserva;
                    String horaReserva;
                    String DNI = (String) session.getAttribute("DNIUsuario");
                    System.out.println("Viajes publicados "+DNI);
                    Calendar c = Calendar.getInstance();
                    int diaHoy = c.get(Calendar.DATE);
                    int mesHoy = c.get(Calendar.MONTH);
                    int añoHoy = c.get(Calendar.YEAR);
                    String fechaHoy = Integer.toString(añoHoy)+"-"+Integer.toString(mesHoy+1)+"-"+Integer.toString(diaHoy);
                    
                    int horasHoy = c.get(Calendar.HOUR_OF_DAY);
                    int minutosHoy = c.get(Calendar.MINUTE);
                    String horaHoy = Integer.toString(horasHoy)+":"+Integer.toString(minutosHoy)+":00";

                    set = con.createStatement();
                    rs = set.executeQuery("SELECT * FROM reservaviaje NATURAL JOIN viaje WHERE DNI='"+DNI+"' and (Fecha_Salida>'"+fechaHoy+"' "
                            + "or (Fecha_Salida='"+fechaHoy+"' and Hora_Salida>'"+horaHoy+"')) ORDER BY Fecha_Salida, Hora_Salida");
                    while (rs.next()) {
                        codigo = rs.getInt("IdViaje");
                        origen = rs.getString("Origen");
                        destino = rs.getString("Destino");
                        fecha = rs.getString("Fecha_Salida");
                        hora = rs.getString("Hora_Salida");
                        precio = rs.getFloat("precio");
                        fechaReserva = rs.getString("fechaReserva");
                        horaReserva = rs.getString("horaReserva");
            %>                         
            <div id='listaviaje'>

                    <b>ID: <%=codigo%></b><br>
                    Origen: <%=origen%> - Destino: <%=destino%><br>
                    Fecha: <%=fecha%> - Hora: <%=hora%> - Precio: <%=precio%>€<br><br>
                    <b>DATOS DE LA RESERVA</b><br>
                    Fecha: <%=fechaReserva%> - Hora: <%=horaReserva%><br>
                    <input type="submit" value="Detalles" id="botonDetallesPorRealizar">
                    <input type="submit" value="Desapuntarse" id="botonViaje">
            </div>
                <%
                        }
                        rs.close();
                        set.close();
                        
                    } catch (Exception ex) {
                        System.out.println("Error en acceso a Reserva_Viajes" + ex);
                    }
                %>    
            </section>
            
        </div>
    </body>
</html>
