/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global db */





function iniciar() {
    
    
    var solicitud = window.indexedDB.open("arabacar05", 1);
    solicitud.addEventListener("error", mostrarerror);
    solicitud.addEventListener("success", comenzar);
    solicitud.addEventListener("upgradeneeded", crearbd);
    
    //agregarUsuario();
    
    
    
    if(document.getElementById("usuario")===null){
        
    }
    else{
        sessionStorage();
    }
    
    
    
    if(document.title === "Registro"){
        alert("estamos en registro");
        nombre = document.getElementById("nombre");
        nombre.addEventListener("input", comprobacionRegistro);
        
        dni = document.getElementById("dni");
        dni.addEventListener("input", comprobacionRegistro);
        
        movil = document.getElementById("movil");
        movil.addEventListener("input", comprobacionRegistro);
        
        email = document.getElementById("email");
        email.addEventListener("input", comprobacionRegistro);
        
        contraseña = document.getElementById("contraseña");
        contraseña.addEventListener("input", comprobacionRegistro);
        
        cajadatos = document.getElementById("cajadatos");

        var archivos = document.getElementById("foto");
        archivos.addEventListener("change", procesar);
    }
    else if (document.title === "Login"){
        email = document.getElementById("email");
        email.addEventListener("input", comprobacionLogin);
        
        contraseña = document.getElementById("contrasenia");
        contraseña.addEventListener("input", comprobacionLogin);
    }
    /*else if (docuemnt.title === "index"){
        
    }*/
}

function mostrarerror(evento) {
    alert("Error :" + evento.code + " " + evento.message);
}

function comenzar(envento){
    bd = evento.target.result;
}

function crearbd(evento) {
    var basededatos = evento.target.result;
    
    var almacen = basededatos.createObjectStore("usuarios", {keyPath: "dni"});
    almacen.createIndex("porDni", "dni", {unique: true});
    almacen.add({DNI: "53982231N", email: "prueba@gmail.com", Contraseña: "1234", nombre: "yo", apellido: "yo", foto: "", movil: "", Marca_Modelo: "opel astra"});
    
    var almacen1 = basededatos.createObjectStore("viaje", {keypath: "IdViaje"});
    almacen1.createIndex("porId", "IdViaje", {unique: true});
    almacen1.add({IdViaje: "1", DNI: "53982231N", Origen: "Vitoria", Fecha_Salida: "22-01-2021", Hora_salida: "18:00", precio: "7"});
    
    var almacen2 = basededatos.createObjectStore("reservaviaje", {keypath:"DNI"});
    almacen2.createIndex("porDni", "DNI", {unique:true});
    almacen2.createIndex("porId", "IdViaje", {unique:true});
    almacen2.add({DNI:"12345678N", IdViaje: "1", Fecha_Y_Hora: ""});
    
}

/*function sesionStorage(){
    if (sessionStorage.length === 0){
        if (localStorage.length === 0){
            document.getElementById("usuario").innerHTML = "";
        }
    }
}*/

function agregarUsuarios() {
    alert("agregar usuarios");
    if(comprobacionRegistro() === true){
        var transaccion = bd.transaction(["usuarios"], "readwrite");
        var almacen = transaccion.objectStore("clientes");
        
        var dni = document.getElementById("dni").value;
        var email = document.getElementById("email").value;
        var contraseña = document.getElementById("contraseña").value;
        var nombre = document.getElementById("nombre").value;
        var apellido = document.getElementById("apellido").value;
        var foto = document.getElementById("foto").value;
        var movil = document.getElementById("movil").value;
        var Marca_modelo = document.getElementById("marcaModelo").value;
    }
    
}
window.addEventListener("load", iniciar);