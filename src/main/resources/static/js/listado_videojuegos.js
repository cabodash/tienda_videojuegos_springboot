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

let reproductores = document.querySelectorAll(".reproductor");

reproductores.forEach(function (item){
    var video = item.querySelector(".video-item");
    document.addEventListener("DOMContentLoaded", function() {

        // Pausar video
        video.pause();

        // Reproducir el video al hacer hover
        item.addEventListener("mouseenter", function() {
            video.play();
        });

        // Pausar el video al quitar el hover
        item.addEventListener("mouseleave", function() {
            video.pause();
        });

        video.addEventListener("ended", function() {
            video.currentTime = 0; 
            video.play();
        });
    });
    
});