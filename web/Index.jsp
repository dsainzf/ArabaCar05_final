<%-- 
    Document   : Index
    Created on : 29 dic. 2020, 19:16:19
    Author     : diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Buscar viajes</title>
        <link rel="icon" type="images/png" href="images/ArabaCar05.PNG">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="style/index.css">
        <script src="js/index.js" type="text/javascript"></script>
       <!-- <script src="js/general.js" type="text/javascript"></script>-->
    </head>
    <body>
        <nav class="navbar">
            <%
                Boolean logueado = false;
                logueado = (Boolean) session.getAttribute("logueado");
                String nombre = (String) session.getAttribute("nombre");
                String imagen = (String) session.getAttribute("imagen");
                String em = (String) session.getAttribute("email");
                
                if(em == null){
                    nombre = "";
                    imagen = "anonimo.png";
                }
               
            %>
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
        <div class="row justify-content-center buscadorViajes ">
            <!--<div class="w-auto d-flex formulario">-->
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
        <!--</div>-->
        <div class="row justify-content-center buscadorViajes">
            <div class="w-auto">
                <table class="formulario table" id="tabla"></table>
            </div>
        </div>
        <h1 align="center"> ¡Encuentra tu viaje con ArabaCar!</h1>
        <img class="centrado" src="images/carretera.png" alt="Solvetic">
    </body>
</html>
