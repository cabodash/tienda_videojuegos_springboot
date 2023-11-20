const formulario = document.querySelector("#formUsuario");
const requisitos = document.querySelector("#requisitos");


let plantillaRegistro = "";


$.get("plantillas_mustache/registro.html", function(data) {
	plantillaRegistro = data;
});


$("#registro").click(function() {
	$("#contenedor").html(plantillaRegistro);


	//js para cargar la imagen de usuario seleccionada
	$("#avatar").change(function(event) {
		if (event.target.files.length > 0) {
			let src = URL.createObjectURL(event.target.files[0]);
			let preview = document.getElementById("preview-img");
			preview.src = src;
			preview.style.display = "block";
		}
	});

	//al pulsar el boton de registrarse
	$("#formUsuario").submit(function(event) {
		event.preventDefault();
		if (comprobarDatos() == true) {
			let formulario = document.forms[0];
			let formData = new FormData(formulario);
			$.ajax("servicioWEB_Usuarios/registrarUsuario", {
				type: "POST",
				data: formData,
				cache: false,
				contentType: false,
				processData: false,
				success: res => {
					alert(res)
				}

			});
		}
		
	});

}); // end registro


function comprobarDatos() {
	let nombre = document.querySelector("#nombre").value;
	let email = document.querySelector("#email").value;
	let pass = document.querySelector("#pass").value;
	let errorNombre = document.querySelector("#errorNombre");
	let errorEmail = document.querySelector("#errorEmail");
	let errorPass = document.querySelector("#errorPass");
	let valid = true;

	if (nombre.trim() != '') {
		errorNombre.classList.add("noErrorText");
	} else {
		errorNombre.classList.remove("noErrorText");
		errorNombre.classList.add("errorText");
		valid = false;
	}
	if (email.trim() != '') {
		errorEmail.classList.add("noErrorText");
	} else {
		errorEmail.classList.remove("noErrorText");
		errorEmail.classList.add("errorText");
		valid = false;
	}

	if (pass.trim() != '') {
		errorPass.classList.add("noErrorText");
	} else {
		errorPass.classList.remove("noErrorText");
		errorPass.classList.add("errorText");
		valid = false;
	}

	return valid;
}
