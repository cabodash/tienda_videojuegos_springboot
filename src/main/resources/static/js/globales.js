//mostrar videojuegos en cliente
let nombre_a_buscar = "";
let comienzo_resultados = 0;
const cantidad_paginacion = 6;




function cargar_plantilla_listado(){
	// cargar css del listado
	if ( $('#estilo-actual').attr("href") != "css/listado_videojuegos.css"){
		$('#estilo-actual').attr('href', 'css/listado_videojuegos.css');
	}

	//Cargar Plantilla del buscador
	let html_videojuegos = "";
	html_videojuegos = Mustache.render(plantillaVideojuegos, videojuegos);
	$("#contenedor").html(html_videojuegos);
	$("#buscador").val(nombre_a_buscar);

	//cargar plantilla_videojuegos
	mostrar_videojuegos();

	//Buscador
	$("#buscador").keyup(function (e) {
		nombre_a_buscar = $(this).val();
		comienzo_resultados = 0;
		mostrar_videojuegos();

	});

	//borrar texto del buscador
	$("#imagen-borrar").click(function (e) {
		console.log("borrar datos busqueda");
		$("#buscador").val("");

	});

	if(comienzo_resultados <= 0){
		$("#enlace_anterior").hide();
	}else{
		$("#enlace_anterior").show();
	}
	let totalVideojuegos = "";
	$("#comienzo_resultado").html(comienzo_resultados);
	$("#total_resultados").html(totalVideojuegos);
	if(comienzo_resultados > totalVideojuegos){
		$("#enlace_siguiente").hide();
	}else{
		$("#enlace_siguiente").show();
	}



	//Paginacion anterior
	$("#enlace_anterior").click(function (e) { 
		e.preventDefault();
		comienzo_resultados -= cantidad_paginacion;
		mostrar_videojuegos();
	});

	//paginacion siguiente
	$("#enlace_siguiente").click(function (e) { 
		e.preventDefault();
		comienzo_resultados += cantidad_paginacion;
		mostrar_videojuegos();
	});



}
function mostrar_videojuegos() {
	//cargar JSON de videojuegos
	$.getJSON("servicioWebVideojuegos/obtenerVideojuegos", { nombre: nombre_a_buscar, comienzo: comienzo_resultados }).done(function (res) {
		
		//antes de mostrar el resultado usando la plantilla podemos prepararlo un poco
		let videojuegos = res.videojuegos;
		for (i in videojuegos) {
			videojuegos[i].fecha_hora_actual = new Date();
			videojuegos[i].precio = videojuegos[i].precio.toString().replace(".", ",");
		}
		totalVideojuegos = res.totalVideojuegos;

		//Carga del html del listado
		let html_listado = "";
		html_listado = Mustache.render(plantillaListado, videojuegos);
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
