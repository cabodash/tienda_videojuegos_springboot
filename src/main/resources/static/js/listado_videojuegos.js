let plantillaVideojuegos = "";
let plantillaDetallesVideojuego = "";


$.get("plantillas_mustache/videojuegos.html", function(data) {

	plantillaVideojuegos = data;
});


$.get("plantillas_mustache/detalles_videojuego.html", function(data) {
	plantillaDetallesVideojuego = data;
});


//Al hacer click en el enlace de videojuegos
$("#videojuegos").click(mostrar_libros);