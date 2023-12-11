let plantillaInicio = "";




$.get("plantillas_mustache/inicio.html", function(data) {
	plantillaInicio = data;
});




$("#inicio").click(function() {
	$("#contenedor").html(plantillaInicio);
});//end inicio

actualizarNavbar();


