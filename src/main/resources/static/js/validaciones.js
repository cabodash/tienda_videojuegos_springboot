let regexp_nombre = /^[a-z áéíóú]{2,10}$/g;
let regexp_email = /^([a-z0-9_\.-]+)@([\da-z]+)\.([a-z\.]{2,10})$/i;
let regexp_contraseña = /^[a-z0-9áéíóúñ]{3,20}$/i;


function validarNombre(id, nombre){
    if(regexp_nombre.test(nombre)){
        return true;
    }  else{
        alert("El nombre solo debe contener letras y espacios, y ser de entre 2 y 10 caracteres");
    }  
}

function validarEmail(id, email){
    if(regexp_email.test(email)){
        return true;
    }  else{
        alert("El email debe ser valido")
    }  
}

function validarContraseña(id, pass){
    if(regexp_contraseña.test(pass)){
        return true;
    }  else{
        alert("La contraseña debe contener solo letras, numeros y debe ser de entre 3 y 20 caracteres");
    }  
}