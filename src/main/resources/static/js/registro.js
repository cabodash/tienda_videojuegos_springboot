const formulario = document.querySelector("#formUsuario");
const requisitos = document.querySelector("#requisitos");


let plantillaRegistro = "";


$.get("plantillas_mustache/registro.html", function(data) {
	plantillaRegistro = data;
});


$("#registro").click(function() {
	$('#estilo-actual').attr('href', 'css/inicio_sesion.css');
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
		if(! validarNombre($("#nombre").val()) ||
		   ! validarEmail($("#email").val()) ||
		   ! validarContraseña($("#pass").val())) {
			event.preventDefault();
			return;
		}
		let formulario = document.forms[0];
		let formData = new FormData(formulario);
		$.ajax("servicioWEB_Usuarios/registrarUsuario", {
			type: "POST",
			data: formData,
			cache: false,
			contentType: false,
			processData: false,
			success: res => {
				alert(res);
			}

		});
		event.preventDefault();
	});
	inputs();

}); // end registro


function inputs(){
	let inputContainers = document.querySelectorAll('.input-box');
	inputContainers.forEach(element => {
		let input = element.querySelector("input");

		isFilled(input, element);
		input.addEventListener('focusout', function () {
			isFilled(input, element);
		});

	});

	let textareaContainers = document.querySelectorAll('.textarea-box');
	textareaContainers.forEach(element => {
		let textarea = element.querySelector("textarea");
		isFilled(textarea, element);
		textarea.addEventListener('focusout', function () {
			isFilled(textarea, element);
		});
	});


	function isFilled(field, element) {
		if (field.value.trim() === '') {
			element.classList.remove('is-filled');
		} else {
			element.classList.add('is-filled');
		}
	}
}

