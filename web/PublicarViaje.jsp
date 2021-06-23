<%-- 
    Document   : PublicarViaje
    Created on : 23-jun-2021, 0:24:13
    Author     : imano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Publicar viaje</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" type="image/png" href="resources/ArabaCarLogo.png"/>
        <link rel="stylesheet" href="css/logueado.css">
        <link rel="stylesheet" href="style/PublicarViaje.css">
    </head>
    <body>
        <div id="contenedor">
            <a title="ArabaCar" href="LogueadoConductor.jsp"><img id="logo" src="resources/ArabaCarLogo.png" alt="logo" height="53"/></a>
            <nav id="navegador">          
              <ul>
                <li><a href="Index.jsp" id="cerrarSesion">Cerrar sesión</a></li>
                <li><a href="ConductorLogueado.jsp">Ver mis reservas</a></li>
                <li><a href="ViajesPublicados.jsp">Ver mis viajes publicados</a>
                </li><li><a class="active" href="PublicarViaje.jsp" >Publicar viaje</a></li>  
                <li><a href="ConductorLogueado.jsp" id="arabacar">ArabaCar</a></li>
              </ul>  
            </nav>
             <section class="registro">
            <form name="formularioPublicar" id="formularioPublicar" method="get" action="PublicarViaje">
                <h4>Formulario de publicación de viaje</h4>
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
                <p>Hora<input class="campos" type="time" name="hora" id="hora" required/></p>
                <p>Precio (€)<input class="campos" type="number" min="0" name="precio" id="precio" step="0.1" required/></p>
                <p><input class="boton" type="submit" value="Publicar" id="botonPublicar"></p>
            </form>
        </section>
            <%
                String existeViaje="";
                existeViaje = (String) session.getAttribute("existeViaje");
                String sitiosIguales="";
                sitiosIguales = (String) session.getAttribute("sitiosIguales");
                String horaNoValida="";
                horaNoValida = (String) session.getAttribute("horaNoValida");
                String fechaAnterior="";
                fechaAnterior = (String) session.getAttribute("fechaAnterior");
                String exito="";
                exito = (String) session.getAttribute("exito");
                
                if(existeViaje=="true"){
            %>
            <script text="javascript/text">
                alert("No puedes tener dos viajes con las mismas características");
            </script>
            <%
                session.setAttribute("existeViaje", "");
                }
                else if(sitiosIguales=="true"){
            %>
            <script text="javascript/text">
                alert("El origen y destino deben ser distintos");
            </script>
            <%
                session.setAttribute("sitiosIguales", "");
                }
                else if(horaNoValida=="true"){
            %>
            <script text="javascript/text">
                alert("La hora debe ser posterior a la actual");
            </script>
            <%
                session.setAttribute("horaNoValida", "");
                }
                else if(fechaAnterior=="true"){
            %>
            <script text="javascript/text">
                alert("La fecha debe ser posterior a la actual");
            </script>
            <%
                session.setAttribute("fechaAnterior", "");
                }
                else if(exito=="true"){
            %>
            <script text="javascript/text">
                alert("Viaje publicado con exito");
                window.location ="ConductorLogueado.jsp";
            </script>
            <%
                session.setAttribute("exito", "");
                }
            %>
        </div>
    </body>
</html>


