<%-- 
    Document   : registro
    Created on : 29 dic. 2020, 19:19:01
    Author     : diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Registro</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" type="images/png" href="images/ArabaCar05.PNG">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/dropzone/5.5.1/min/dropzone.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="style/registro.css">
        <script src="js/registro.js" type="text/javascript"></script>
        <!--<script src="js/general.js" type="text/javascript"></script>-->
    </head>
    <body>
        <nav class="navbar">
            <%
                Boolean logueado = false;
                logueado = (Boolean) session.getAttribute("logueado");
                String nombre = (String) session.getAttribute("nombre");
                String foto = (String) session.getAttribute("foto");
                String email = (String) session.getAttribute("email");
                
                if(email == null){
                    nombre = "JuanIgnacio";
                    foto = "anonimo.png";
                }
               
            %>
            <div class="row mx-auto w-100 justify-content-end">
                <div class="col-lg-4 logo">
                    <a href="Index.html"><img src="images/logo.png"></a>
                </div>
                <div class="col-lg-4 tema">
                    <div class="float-right"><input type="button" class="btn btn-tema" id="tema" value=""></div>
                </div>
            </div>
        </nav>
        <div class="container registro">
            <div class="col-lg-6 formulario mx-auto">
                <h1 class="text-center">Registro</h1>
                <form id="formRegistro" class="form-group" onsubmit="guardarUsuario(); return false;" action="Controlador" method="post">
                    <p>Nombre: <input type="text" class="form-control" id="nombre" name="nombre" required></p>
                    <p>Apellido: <input type="text" class="form-control" id="apellido" name="apellido" required></p>
                    <p>DNI: <input type="text" class="form-control" id="dni" name="dni" required></p>
                    <p>Email: <input type="email" class="form-control" id="email" name="email" pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,}$" required></p>
                    <p>Contrase&ntilde;a: <input type="password" class="form-control" id="contrasenia" name="contrasenia" required></p>
                    <p>Confirmar contrase&ntilde;a: <input type="password" class="form-control" id="confContrasenia" name="confirmacion" required></p>
                    <p>Foto: <input type="file" class="dropzone form-control" id="foto" name="foto" accept="image/*" required onchange="getImage()"></p>
                    <p>Edad: <input type="number" class="form-control" id="edad" name="edad" min="18" required></p>
                    <p>Marca y modelo del coche: <input type="text" class="form-control" id="marcaModelo" name="marcaModelo"></p>
                    <input type="submit" name="registrarse" class="btn btn-light mt-3" value="Guardar">
                </form>
            </div>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/dropzone/5.5.1/min/dropzone.min.js"></script>
    </body>
</html>
