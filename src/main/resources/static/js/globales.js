//mostrar videojuegos en cliente
let nombre_a_buscar = "";
function mostrar_videojuegos() {
	// cargar css del listado
	$('#estilo-actual').attr('href', 'css/listado_videojuegos.css');
	$.getJSON("servicioWebVideojuegos/obtenerVideojuegos", { nombre: nombre_a_buscar }).done(function (res) {
		let texto_html = "";
		//antes de mostrar el resultado usando la plantilla podemos prepararlo un poco
		for (i in res) {
			res[i].fecha_hora_actual = new Date();
			res[i].precio = res[i].precio.toString().replace(".", ",");

		}
		texto_html = Mustache.render(plantillaVideojuegos, res);
		$("#contenedor").html(texto_html);
		$("#buscador").val(nombre_a_buscar);
		$("#buscador").focus();

		//Entrar en detalles de pedido
		$(".reproductor").click(function (e) {
			let id_producto = $(this).attr("id-producto");
			$.getJSON("servicioWebVideojuegos/obtenerVideojuegoDetalles",
				{
					id: id_producto
				}
			).done(function (res) {
				let html = Mustache.render(plantillaDetallesVideojuego, res);
				console.log(res);
				$("#contenedor").html(html);

				//Indicamos que hace el enlace comprar
				$(".enlace_comprar_listado_principal").click(function (res) {
					if (nombre_login != "") {
						let id_producto = $(this).attr("id-producto");
						$.post("servicioWebCarrito/agregarVideojuego",
							{
								id: id_producto,
								cantidad: 1
							}
						).done(function (res) {
							alert(res);
						});
					} else {
						alert("Tienes que identificarte para comprar productos");
					}
				});
			});
		});

		//Buscador
		$("#buscador").keyup(function (e) {
			nombre_a_buscar = $(this).val();
			mostrar_videojuegos();

		});

		//borrar texto del buscador
		$("#imagen-borrar").click(function (e) {
			console.log("borrar datos busqueda");
			$("#buscador").val("");

		});







	}).done(cargar_reproductores);


}


//cargar la funcionalidad de los reproductores de video de los listados
function cargar_reproductores() {
	//Funcionamiento videos
	let reproductores = document.querySelectorAll(".reproductor");

	reproductores.forEach(function (item) {
		var video = item.querySelector(".video-item");
		video.addEventListener('canplaythrough', function () {
			video.play();
		});
		// Pausar video
		video.pause();

		// Reproducir el video al hacer hover
		item.addEventListener("mouseenter", function () {
			video.play();
		});

		// Pausar el video al quitar el hover
		item.addEventListener("mouseleave", function () {
			video.pause();
		});

		video.addEventListener("ended", function () {
			video.currentTime = 0;
			video.play();
		});

	});
}
