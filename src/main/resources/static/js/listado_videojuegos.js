let plantillaVideojuegos = "";
let plantillaListado = "";
let plantillaDetallesVideojuego = "";

$.get("plantillas_mustache/listado_videojuegos.html", function(data) {
	plantillaListado = data;
});

$.get("plantillas_mustache/videojuegos.html", function(data) {
	plantillaVideojuegos = data;
	setTimeout(mostrar_videojuegos, 200);
});


$.get("plantillas_mustache/detalles_videojuego.html", function(data) {
	plantillaDetallesVideojuego = data;
});


//Al hacer click en el enlace de videojuegos
$("#videojuegos").click(cargar_plantilla_listado);

