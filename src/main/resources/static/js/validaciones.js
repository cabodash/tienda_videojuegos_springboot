let regexp_nombre = /^[a-z áéíóú]{2,10}$/g;
let regexp_email = /^[a-zA-Z0-9._-]{4,30}@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
let regexp_contraseña = /^[a-z0-9áéíóúñ]{3,20}$/i;




function validarNombre(id, nombre) {
    if (regexp_nombre.test(nombre)) {
        $(`#${id}`).removeClass("error");
        $(`#${id}`).addClass("no-error");
        return true;
    } else {
        $(`#${id}`).removeClass("no-error");
        $(`#${id}`).addClass("error");
        return false;
    }
}

function validarEmail(id, email) {
    if (regexp_email.test(email)) {
        $(`#${id}`).removeClass("error");
        $(`#${id}`).addClass("no-error");
        return true;
    } else {
        
        $(`#${id}`).removeClass("no-error");
        $(`#${id}`).addClass("error");
        return false;
    }
}

function validarContraseña(id, pass) {
    if (regexp_contraseña.test(pass)) {
        $(`#${id}`).removeClass("error");
        $(`#${id}`).addClass("no-error");
        return true;
    } else {
        $(`#${id}`).removeClass("no-error");
        $(`#${id}`).addClass("error");
        return false;
    }
}