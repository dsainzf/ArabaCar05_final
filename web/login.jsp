<%-- 
    Document   : login
    Created on : 29 dic. 2020, 19:18:39
    Author     : diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" type="images/png" href="images/ArabaCar05.PNG">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="style/login.css">
        <script src="js/login.js" type="text/javascript"></script>
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
                </div>
            </div>
        </nav>
        <div class="container registro">
            <div class="col-lg-6 formulario mx-auto">
                <h1 class="text-center">Login</h1>
                <form id="formLogin" class="form-group" onsubmit="login(); return false;" action="Controlador" method="doPost">
                    <p>Email: <input type="text" class="form-control" id="email" name="email" required></p>
                    <p>Contrase&ntilde;a: <input type="password" class="form-control" id="contrasenia" name="contrasenia" required></p>
                    <input type="button" class="btn btn-primary mt-3" id="crearCuenta" value="Crear cuenta">
                    <input type="submit" name="btnLogin" class="btn btn-light float-right mt-3" value="Entrar">
                </form>
            </div>
        </div>
    </body>
</html>
