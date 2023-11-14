let plantillaIdentificarUsuario = "";

$.get("plantillas_mustache/identificar_usuario.html", function(data) {
	plantillaIdentificarUsuario = data;
});


//Identificarse
$("#identificar").click(function() {
	$("#contenedor").html(plantillaIdentificarUsuario);
	
	//ver si el usuario guardo sus datos de inicio de sesion en una cookie y en tal caso ponerlos en los input correspondientes
	if (typeof(Cookies.get("email")) != "undefined"){
		$("#email").val(Cookies.get("email"));
	}
	if (typeof(Cookies.get("pass")) != "undefined"){
		$("#pass").val(Cookies.get("pass"));
	}
	$("#form_login").submit(function(e) {
		$.post("servicioWEB_Usuarios/identificarUsuario",
			{
				email: $("#email").val(),
				pass: $("#pass").val()
			}
		).done(function(res) {
			if (res.split(",")[0] == "ok") {
				//el usuario se identifico correctamente
				nombre_login = res.split(",")[1];
				$("#mensaje_login").html("Identificado como: " + nombre_login);
				//Si se activo el checkbox de recordar datos
				if($("#recordarDatos").prop("checked")){
					Cookies.set("email", $("#email").val(), {
						expires: 100	
					});
					Cookies.set("pass", $("#pass").val(), {
						expires: 100	
					});
				}
			} else {
				alert(res);
			}
		});
		e.preventDefault();
	});

});

