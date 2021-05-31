<%-- 
    Document   : Index
    Created on : 29 dic. 2020, 19:16:19
    Author     : diego
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.util.Base64"%>
<%@page import="java.sql.Blob"%>
<%@page import="utils.BD"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        HttpSession s = request.getSession();
        
        if (s.getAttribute("email") == null){
            
            out.println("<script>");
            out.println("window.onload = function (){    limpiar();};function limpiar() {    $('div').remove('#0');    $('div').remove('#5');    $('div').remove('#6');}");
            out.println("</script>");
            
            System.out.println(request.getSession().getAttribute("coche") + "<=coche 1");
            
        } else if (s.getAttribute("marcaModelo") == null){
            System.out.println(request.getSession().getAttribute("coche") + "<=coche 2");

            out.println("<script>");
            out.println("window.onload = function (){    limpiar();    };function limpiar() {          $('a').remove('#1_1');    $('a').remove('#1_2');   $('a').remove('#1_3');    $('a').remove('#4');          $('div').remove('#5');          $('h1').remove('#7');}");
            out.println("</script>");
        } else if (s.getAttribute("marcaModelo") != null){
            System.out.println(request.getSession().getAttribute("coche") + "<=coche 3");

            out.println("<script>");
            out.println("window.onload = function (){    limpiar();    };function limpiar() {    $('a').remove('#4');    $('h1').remove('#7');}");
            out.println("</script>");
        }
        %>
        
        <script>
            function checkIt() {

                if (confirm('¿Deseas Cerrar Sesion?')) {
                    return true;
                } else {
                    return false;
                }
            }
        </script>
        
        <%
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
                    rsName = stName.executeQuery("select * from usuario where email = '" + e + "'");
                    rsName.next();
                    
                    n = rsName.getString("nombre");
                    n = "Hola" + n;
                    //System.out.println("Bienvenido/a " + n);
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
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Inicio</title>
        <link rel="icon" type="images/png" href="images/ArabaCar05.PNG">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="style/index.css">
        <script src="js/index.js" type="text/javascript"></script>
       <!-- <script src="js/general.js" type="text/javascript"></script>-->
    </head>
    <body>
        <nav class="navbar">
            <div class="row mx-auto w-100 justify-content-end">
                <div class="col-lg-4 logo">
                    <a href="index.html"><img src="images/logo.png"></a>
                </div>
                <div class="col-lg-4 tema">
                    <div class="float-right"><input type="button" class="btn btn-tema" id="tema" value=""></div>
                    <!--<input class="float-right btn btn-light" type="submit" id="registro" value="Registrarse">-->
                    <a href="registro.jsp">Registrarse</a>
                    <!--<input class="float-right btn btn-light" type="button" id="login" value="Login">-->
                    <a href="login.jsp">login</a>
                    <input class="float-right btn btn-light" type="button" id="cerrarSesion" value="Cerrar sesion">
                    <input class="float-right btn btn-light" type="button" id="crearViaje" value="Crear viaje">
                    <!--<input class="float-right btn btn-light" type="button" id="viajesP" value="Mis Viajes">-->
                    <a href="verViajesC.jsp">Mis Viajes </a>
                </div>
            </div>
        </nav>  
        <!--<div class="row justify-content-center buscadorViajes ">
            <div class="w-auto d-flex formulario">
            <form action="Controlador" method="doPost">
                <select id="origen" class="form-control w-auto" name="origen" required>
                    <option value="">-Origen-</option>
                    <option id="vitoriaO" value="vitoria">Vitoria</option>
                    <option id="bilbaoO" value="bilbao">Bilbao</option>
                    <option id="donostiaO" value="donostia">Donostia</option>
                </select>
                <select id="destino" class="form-control w-auto" name="destino" required>
                    <option value="">-Destino-</option>
                    <option id="vitoriaD" value="vitoria">Vitoria</option>
                    <option id="bilbaoD" value="bilbao">Bilbao</option>
                    <option id="donostiaD" value="donostia">Donostia</option>
                </select>
                <input type="datetime-local" class="form-control w-auto" id="fechaHora" name="fechaHora" required>
                <input type="submit" class="btn btn-light" id="buscarViajes" value="Buscar viajes"> 
            </div>
            </form>
        </div>-->
        <div class="row justify-content-center buscadorViajes">
            <div class="w-auto">
                <table class="formulario table" id="tabla"></table>
            </div>
        </div>
        <h1 align="center"> ¡Encuentra tu viaje con ArabaCar!</h1>
        <div>
            <a href="BuscarViaje.jsp"><input type="button" id="BuscarViaje" value="Busca tu viaje" class="row justify-content-center botonBuscar"></a>
            </div>  
        <img class="centrado" src="images/carretera.png" alt="Solvetic">
    </body>
</html>
