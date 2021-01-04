<%-- 
    Document   : verViajesC
    Created on : 29 dic. 2020, 19:26:32
    Author     : diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Consulta tus viajes</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
         <link rel="icon" type="images/png" href="images/favicon.PNG">
          <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
         <script src="js/altaViaje.js" type="text/javascript"></script>
         <link rel="stylesheet" type="text/css" href="style/verViajesC.css">
         <script src="js/verViajesC.js" type="text/javascript"></script>
         
    </head>
    <body>
        <div> 
            <form name="formulario">
                <p>Fecha: <input type="datetime-local" id="fechaConductor" name="fechaConductor" ></p> <br/><br/>
                <input type="button" name="botonPost" id="botonPost" class="boton" value="Mostrar viajes posteriores">
                <br/><br/>
                <input type="button" name="botonAnt" id="botonAnt" class="boton" value="Mostrar viajes anteriores">
                <br/>
            </form>
            <!--<section id="zonadatos">   

                            </section>-->
        <table id="tabla">
        </table>
        </div>
    </body>
</html>
