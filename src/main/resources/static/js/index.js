let plantillaInicio = "";

//variable que indica el nombre de usuario si se ha identificado correctamente
let nombre_login = "";


$.get("plantillas_mustache/inicio.html", function(data) {
	plantillaInicio = data;
});




$("#inicio").click(function() {
	$("#contenedor").html(plantillaInicio);
});//end inicio


