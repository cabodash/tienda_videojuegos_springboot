let regexp_nombre = /^[a-z áéíóú]{2,10}$/g;
let regexp_email = /^[a-zA-Z0-9._-]{4,30}@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
let regexp_contraseña = /^[a-z0-9áéíóúñ]{3,20}$/i;

//Checkout (alguna de arriba usa tambien)
let regexp_apellidos = /^[a-z áéíóúñ]{2,10}$/g;
let regexp_direccion = /^[a-zA-Z0-9 .,áéíóúñ]{5,20}$/;
let regexp_ciudad = /^[a-z áéíóúñ]{3,15}$/g;
let regexp_codigo_postal = /^[0-9]{5,8}$/;
let regexp_provincia = /^[a-z áéíóúñ]{3,20}$/g;
let regexp_numero_tarjeta = /^[0-9]{16}$/;
let regexp_titular_tarjeta = /^[a-z áéíóúñ]{2,30}$/i;
let regexp_fecha_caducidad = /^(0[1-9]|1[0-2])\/\d{2}$/;
let regexp_cvv_tarjeta = /^[0-9]{3}$/;
let regexp_persona_contacto = /^[a-z áéíóúñ]{3,10}$/i;
let regexp_telefono_contacto = /^\+\d{11,14}$/;




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
function validarApellidos(id, apellidos) {
    if (regexp_apellidos.test(apellidos)) {
        $(`#${id}`).removeClass("error");
        $(`#${id}`).addClass("no-error");
        return true;
    } else {
        $(`#${id}`).removeClass("no-error");
        $(`#${id}`).addClass("error");
        return false;
    }
}

function validarDireccion(id, direccion) {
    if (regexp_direccion.test(direccion)) {
        $(`#${id}`).removeClass("error");
        $(`#${id}`).addClass("no-error");
        return true;
    } else {
        $(`#${id}`).removeClass("no-error");
        $(`#${id}`).addClass("error");
        return false;
    }
}

function validarCiudad(id, ciudad) {
    if (regexp_ciudad.test(ciudad)) {
        $(`#${id}`).removeClass("error");
        $(`#${id}`).addClass("no-error");
        return true;
    } else {
        $(`#${id}`).removeClass("no-error");
        $(`#${id}`).addClass("error");
        return false;
    }
}

function validarCodigoPostal(id, codigoPostal) {
    if (regexp_codigo_postal.test(codigoPostal)) {
        $(`#${id}`).removeClass("error");
        $(`#${id}`).addClass("no-error");
        return true;
    } else {
        $(`#${id}`).removeClass("no-error");
        $(`#${id}`).addClass("error");
        return false;
    }
}

function validarProvincia(id, provincia) {
    if (regexp_provincia.test(provincia)) {
        $(`#${id}`).removeClass("error");
        $(`#${id}`).addClass("no-error");
        return true;
    } else {
        $(`#${id}`).removeClass("no-error");
        $(`#${id}`).addClass("error");
        return false;
    }
}
function validarNumeroTarjeta(id, numeroTarjeta) {
    if (regexp_numero_tarjeta.test(numeroTarjeta)) {
        $(`#${id}`).removeClass("error");
        $(`#${id}`).addClass("no-error");
        return true;
    } else {
        $(`#${id}`).removeClass("no-error");
        $(`#${id}`).addClass("error");
        return false;
    }
}

function validarTitularTarjeta(id, titularTarjeta) {
    if (regexp_titular_tarjeta.test(titularTarjeta)) {
        $(`#${id}`).removeClass("error");
        $(`#${id}`).addClass("no-error");
        return true;
    } else {
        $(`#${id}`).removeClass("no-error");
        $(`#${id}`).addClass("error");
        return false;
    }
}

function validarFechaCaducidad(id, fechaCaducidad) {
    if (regexp_fecha_caducidad.test(fechaCaducidad)) {
        $(`#${id}`).removeClass("error");
        $(`#${id}`).addClass("no-error");
        return true;
    } else {
        $(`#${id}`).removeClass("no-error");
        $(`#${id}`).addClass("error");
        return false;
    }
}

function validarCvvTarjeta(id, cvvTarjeta) {
    if (regexp_cvv_tarjeta.test(cvvTarjeta)) {
        $(`#${id}`).removeClass("error");
        $(`#${id}`).addClass("no-error");
        return true;
    } else {
        $(`#${id}`).removeClass("no-error");
        $(`#${id}`).addClass("error");
        return false;
    }
}

function validarPersonaContacto(id, persona_contacto) {
    if (regexp_persona_contacto.test(persona_contacto)) {
        $(`#${id}`).removeClass("error");
        $(`#${id}`).addClass("no-error");
        return true;
    } else {
        $(`#${id}`).removeClass("no-error");
        $(`#${id}`).addClass("error");
        return false;
    }
}

function validarTelefonoContacto(id, telefono_contacto) {
    if (regexp_telefono_contacto.test(telefono_contacto)) {
        $(`#${id}`).removeClass("error");
        $(`#${id}`).addClass("no-error");
        return true;
    } else {
        $(`#${id}`).removeClass("no-error");
        $(`#${id}`).addClass("error");
        return false;
    }
}






