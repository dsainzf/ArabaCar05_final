<%-- 
    Document   : ConductorLogueado
    Created on : 07-jun-2021, 19:02:17
    Author     : imano
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.util.Base64"%>
<%@page import="java.sql.Blob"%>
<%@page import="utils.BD"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>ArabaCar</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" type="image/png" href="images/ArabaCar05.png"/>
        <link rel="stylesheet" href="style/logueado.css">
        <script src="js/cerrarSesion.js"></script>
        
        <%
            HttpSession s = request.getSession();
            
            Connection conn;
            String n = "";
            String imgDataBase64= "";
            try {
                Statement stName;
                ResultSet rsName;
                
                System.out.println("Iniciando el JSP");
                conn = BD.getConexion();
                
                String e = (String) s.getAttribute("email");
                
                if (s.getAttribute("email") != null) {
                    
                    stName = conn.createStatement();
                    rsName = stName.executeQuery("select * from usuarios where Email = '" + e + "'");
                    rsName.next();
                    
                    n = rsName.getString("nombre");
                    System.out.println(n);
                    //el nombre se mostrará en el label que viene despues
                    //objeto Blob recuperado de la BD
                    Blob image = rsName.getBlob("foto");
                    byte[] imgData = null;
                    imgData = image.getBytes(1, (int) image.length());
                    imgDataBase64 = new String(Base64.getEncoder().encode(imgData));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error en la consulta!");
            }
            %>
    </head>
    <body>
        <div id="contenedor">
            <a title="logo" href="ConductorLogueado.jsp"><img id="logo" src="images/ArabaCar05.png" alt="logo" height="53"/></a>
            <nav id="navegador">
                <p id="saludo">Hola, <%=n%> </p> 
              <ul>
                <li><a id="cerrarSesion" onclick="cerrarSesion()">Cerrar sesión</a></li>
                <li><a href="ReservasConductor.jsp">Ver mis reservas</a></li>
                <li><a href="VerViajesPublicados.jsp">Ver mis viajes publicados</a>
                </li><li><a href="PublicarViaje.jsp" >Publicar viaje</a></li>  
                <li><a class="active" href="ConductorLogueado.jsp" id="arabacar">ArabaCar</a></li>
              </ul>  
            </nav>
            <section id="cuerpo">
                <h2>¿A dónde quieres ir?</h2>
                <!--<img src="resources/vitoria.jpg" alt="Vitoria" class="vitoria" title="Ciudad de Vitoria-Gasteiz" id="ciudad"/> 
                <img src="resources/donosti.png" alt="Vitoria" class="donosti" title="Ciudad de Donosti" id="ciudad"/> 
                <img src="resources/bilbao.jpg" alt="Vitoria" class="bilbao" title="Ciudad de Bilbao" id="ciudad"/> -->
                <form name="formularioBusqueda" id="formularioBusqueda" action="BuscarViaje" method="post">
                    <p>Origen<select name="origen" class="campos" id="origen" required>
                                    <option selected disabled value="">Elija el origen</option>        
                                    <option value="Vitoria">Vitoria-Gasteiz</option>
                                    <option value="Bilbao">Bilbao</option>
                                    <option value="Donostia">Donostia</option>
                                </select>
                    </p>
                    <p>Destino<select name="destino" class="campos" id="destino" required>
                                    <option selected disabled value="">Elija el destino</option>
                                    <option value="Vitoria">Vitoria-Gasteiz</option>
                                    <option value="Bilbao">Bilbao</option>
                                    <option value="Donostia">Donostia</option>
                                </select>
                    </p>    
                    <p>Fecha<input class="campos" type="date" name="fecha" id="fecha" required/></p>
                    <p><input type="submit" value="Buscar" class="boton" id="botonBuscar"></p>
                <!--<img src="resources/car-driving.gif" alt="Coche en movimiento" class="coche"> -->   
                </form>
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
                <%  
                    String sitiosIguales ="";
                    sitiosIguales = (String) session.getAttribute("sitiosIguales");
                    String fechaAnterior;
                    fechaAnterior = (String) session.getAttribute("fechaAnterior");
                    if(sitiosIguales=="true"){
                %>
                        <script type="text/javascript"> 
                            alert("El origen y el destino deben ser distintos");
                        </script>
                <%
                    }
                    else if(fechaAnterior=="true"){
                %>
                        <script type="text/javascript">    
                            alert("La fecha no puede ser anterior al dia de hoy");
                        </script>
                <%
                    }
                    else{
                    ArrayList<String[]> resultadoViajes = new ArrayList<>();
                    resultadoViajes = (ArrayList) session.getAttribute("resultadoViajes");
                    if(resultadoViajes != null){
                %>
                <section id="viajesBuscados">
                    <h5>Viajes disponibles</h5>
                    <%
                        boolean hayViajes = (boolean) session.getAttribute("hayViajes");
                        if(!hayViajes){
                    %>
                    No hay viajes con esas características
                    <%
                        }
                        else{
                            for(int i=0; i<resultadoViajes.size();i++){
                                String codigo = resultadoViajes.get(i)[0]; 
                                String origen = resultadoViajes.get(i)[1];
                                String destino = resultadoViajes.get(i)[2];
                                String fecha = resultadoViajes.get(i)[3];
                                String hora = resultadoViajes.get(i)[4];
                                String precio = resultadoViajes.get(i)[5];
                    %>
                    <div id='listaviaje'>

                    ID: <%=codigo%><br>
                    Origen: <%=origen%> - Destino: <%=destino%><br>
                    Fecha: <%=fecha%> - Hora: <%=hora%> - Precio: <%=precio%>€
                    </div>
                    <%
                            }
                        }
                    %>
                </section>
                <%
                    }
                }
                %>
                <!--<h3>¿Por qué viajar con ArabaCar?</h3>
                <div class="info"><h4>Práctico</h4>Accede a miles de viajes y encuentra con rapidez gente cercana a ti con tu mismo destino.<hr color="#9999ff" size="4">
                    <img id="chasquido" src="resources/chasquido.png" height="85"></div>
                <div class="info"><h4>Sencillo</h4>Indícanos tu destino deseado y te mostraremos todas las opciones disponibles.<hr color="#9999ff" size="4">
                    <img id="foto" src="resources/tick.png" height="70"></div>         
                <div class="info"><h4>Directo</h4>Llega directamente a tu destino olvidándote de las colas y las esperas en la estación.<hr color="#9999ff" size="4">
                    <img id="foto" src="resources/ubicacion.png" height="70"></div>
            
            <section id="pieDePagina">
                <a id="gracias">¡Gracias por usar ArabaCar!</a><br/>
                <a id="subtitulo">La mejor forma de desplazarse</a><hr class="linea" color="#9999ff" size="1" width="1200">
                <ul id="listaFin">
                     <li><a href="#">Sobre nosotros</a></li>  
                     <li><a href="#">Ayuda</a></li>
                     <li><a href="#">Contacto</a></li>
                </ul><br/><br/><hr class="linea" color="#9999ff" size="1" width="1200">  
                <a id="compañia">© ArabaCar 2020</a>
                <a id="autores">Álvaro Martínez, Unai Ruiz</a><br/>
                <br/>
                <br/>
                <br/>
                <hr color="#9999ff" size="10" width="1515">  
            </section>
                </section>
        </div>-->
    </body>
</html>
