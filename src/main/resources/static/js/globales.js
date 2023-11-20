function mostrar_videojuegos(){
	// cargar css del listado
	$('#estilo-actual').attr('href', 'css/listado_videojuegos.css');
	$.getJSON("servicioWebVideojuegos/obtenerVideojuegos", res => {
		let texto_html = "";
		//antes de mostrar el resultado usando la plantilla podemos prepararlo un poco
		for (i in res) {
			res[i].fecha_hora_actual = new Date();
			res[i].precio = res[i].precio.toString().replace(".", ",");

		}
		texto_html = Mustache.render(plantillaVideojuegos, res);
		$("#contenedor").html(texto_html);
		//Indicamos que hace el enlace comprar

		


		$(".enlace_comprar_listado_principal").click(function(res) {
			if (nombre_login != "") {
				let id_producto = $(this).attr("id-producto");
				$.post("servicioWebCarrito/agregarVideojuego",
					{
						id: id_producto,
						cantidad: 1
					}
				).done(function(res) {
					alert(res);
				});
			} else {
				alert("Tienes que identificarte para comprar productos");
			}
		});
		
		//Indicar que hace el enlace de detalles
		$(".enlace_ver_detalles").click(function() {
			let id_producto = $(this).attr("id-producto");
			$.getJSON("servicioWebVideojuegos/obtenerVideojuegoDetalles",
				{
					id: id_producto
				}
			).done(
				function (res){
					let html = Mustache.render(plantillaDetallesVideojuego, res);
					console.log(res);
					$("#contenedor").html(html);
				}
			);

		});


	}).done(cargar_reproductores);

	
}

function cargar_reproductores(){
	//Funcionamiento videos
	let reproductores = document.querySelectorAll(".reproductor");

	reproductores.forEach(function (item){
		var video = item.querySelector(".video-item");
			video.addEventListener('canplaythrough', function() {
				video.play();
			});
			// Pausar video
			video.pause();

			// Reproducir el video al hacer hover
			item.addEventListener("mouseenter", function() {
				console.log("hover");
				video.play();
			});

			// Pausar el video al quitar el hover
			item.addEventListener("mouseleave", function() {
				console.log("no-hover");
				video.pause();
			});

			video.addEventListener("ended", function() {
				console.log("reset");
				video.currentTime = 0; 
				video.play();
			});
		
	});
}
