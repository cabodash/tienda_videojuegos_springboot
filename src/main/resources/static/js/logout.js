//logout
$("#logout").click(function(){
	$.ajax("servicioWEB_Usuarios/logout", {
		success:function(res){
			if(res == "ok"){
				$("#contenedor").html("Hasta pronto " + nombre_login);
				$("#mensaje_login").html("No estas identificado");
				nombre_login = "";
				actualizarNavbar();
			}
		}
	});
});

