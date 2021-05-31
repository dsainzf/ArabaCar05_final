<%-- 
    Document   : BuscarViaje
    Created on : 31-may-2021, 12:02:53
    Author     : imano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Inicio</title>
        <link rel="icon" type="images/png" href="images/ArabaCar05.PNG">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style/BuscarViaje.css">
        <title>Busca tu viaje</title>
    </head>
    <body>
        <nav class="navbar">
        <div class="row mx-auto w-100 justify-content-end">
                <div class="col-lg-4 logo">
                    <a href="Index.jsp"><img src="images/logo.png"></a>
                </div>
                <div class="col-lg-4 tema">
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
        <div class="row justify-content-center buscadorViajes">
            <div class="w-auto d-flex formulario">
            <form action="Controlador" method="doPost">
                <select id="origen" class="form-control w-auto" name="origen" required>
                    <option value="">-Origen-</option>
                    <option id="vitoriaO" value="vitoria">Vitoria</option>
                    <option id="bilbaoO" value="bilbao">Bilbao</option>
                    <option id="donostiaO" value="donostia">Donostia</option>
                </select>
                <br>
                <br>
                <select id="destino" class="form-control w-auto" name="destino" required>
                    <option value="">-Destino-</option>
                    <option id="vitoriaD" value="vitoria">Vitoria</option>
                    <option id="bilbaoD" value="bilbao">Bilbao</option>
                    <option id="donostiaD" value="donostia">Donostia</option>
                </select>
                <br>
                <br>
                <input type="datetime-local" class="form-control w-auto" id="fechaHora" name="fechaHora" required>
                <br>
                <br>
                <input type="submit" class="btn btn-light" id="buscarViajes" value="Buscar viajes"> 
            </div>
            </form>
        </div>
    </body>
</html>
