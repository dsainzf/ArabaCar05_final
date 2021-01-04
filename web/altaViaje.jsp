<%-- 
    Document   : altaViaje
    Created on : 29 dic. 2020, 19:18:22
    Author     : diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" type="images/png" href="images/ArabaCar05.PNG">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="style/altaViaje.css">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="js/altaViaje.js" type="text/javascript"></script>
        <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAVCDMQJngDsQw4KbVBRTle0hyf9mlwaWc&callback=initMap&libraries=&v=weekly" defer></script>
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
            <div class="row">
                <div class="col-lg-6 formulario">
                    <h1 class="text-center">Crear viaje</h1>
                    <form id="formAltaViaje" onsubmit="guardarViaje(); return false;" method="post">
                        <p>
                            <select id="origen" class="form-control" required>
                                <option value="">-Origen-</option>
                                <option id="vitoriaO" value="vitoria">Vitoria</option>
                                <option id="bilbaoO" value="bilbao">Bilbao</option>
                                <option id="donostiaO" value="donostia">Donostia</option>
                            </select>
                        </p>
                        <p>
                            <select id="destino" class="form-control" required>
                                <option value="">-Destino-</option>
                                <option id="vitoriaD" value="vitoria">Vitoria</option>
                                <option id="bilbaoD" value="bilbao">Bilbao</option>
                                <option id="donostiaD" value="donostia">Donostia</option>
                            </select>
                        </p>
                        <p>Fecha y hora: <input type="datetime-local" class="form-control" id="fechaHora" name="fechaHora" required></p>
                        <p><input type="text" class="form-control coordenada" id="latOrigen" name="latOrigen" ></p>
                        <p><input type="text" class="form-control coordenada" id="lonOrigen" name="lonOrigen" ></p>
                        <p><input type="button" class="btn btn-info" id="mostrarMapaOrigen" name="mostrarMapaOrigen" value="Seleccionar origen"></p>
                        <p><input type="text" class="form-control coordenada" id="latDestino" name="latDestino" ></p>
                        <p><input type="text" class="form-control coordenada" id="lonDestino" name="lonDestino" ></p>
                        <p><input type="button" class="btn btn-info" id="mostrarMapaDestino" name="mostrarMapaDestino" value="Seleccionar destino"></p>
                        <p>Precio: <input type="number" class="form-control" id="precio" name="precio" step="0.01" min="0.01" required></p>
                        <input type="submit" class="btn btn-light mt-3" value="Guardar">
                    </form>
                </div>
                <div class="col-lg-6">
                    <div id="map"></div>
                </div>
            </div>
        </div>
    </body>
</html>
