//mostrar videojuegos en cliente
let dato_a_buscar = "";
let comienzo_resultados = 0;
const cantidad_paginacion = 6;

//variable que indica el nombre de usuario si se ha identificado correctamente
let nombre_login = "";

function cargar_plantilla_listado() {
	// cargar css del listado
	if ($('#estilo-actual').attr("href") != "css/listado_videojuegos.css") {
		$('#estilo-actual').attr('href', 'css/listado_videojuegos.css');
	}

	//Cargar Plantilla del buscador
	$("#contenedor").html(plantillaVideojuegos);
	$("#buscador").val(dato_a_buscar);

	typingTimer = null;
	//Buscador
	$("#buscador").keyup(function (e) {
		clearTimeout(typingTimer);
		typingTimer = setTimeout(function () {
			dato_a_buscar = $("#buscador").val();
			comienzo_resultados = 0;
			mostrar_videojuegos();
		}, 500);


	});

	//borrar texto del buscador
	$("#borrar-texto-busqueda").click(function (e) {
		console.log("borrar datos busqueda");
		$("#buscador").val("");
		dato_a_buscar = "";
		comienzo_resultados = 0;
		mostrar_videojuegos();

	});


	//cargar plantilla_videojuegos
	mostrar_videojuegos();

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
	$.getJSON("servicioWebVideojuegos/obtenerVideojuegos", { dato: dato_a_buscar, comienzo: comienzo_resultados }).done(function (res) {

		//antes de mostrar el resultado usando la plantilla podemos prepararlo un poco
		let videojuegos = res.videojuegos;
		for (i in videojuegos) {
			videojuegos[i].fecha_hora_actual = new Date();
			videojuegos[i].precio = videojuegos[i].precio.toString().replace(".", ",");
		}
		let totalVideojuegos = "";
		totalVideojuegos = res.totalVideojuegos;

		//Poner foto por defecto si no tiene


		//Carga del html del listado
		let html_listado = Mustache.render(plantillaListado, videojuegos);
		$(".listado-videojuegos").html(html_listado);

		//esconder y mostrar paginacion
		if (comienzo_resultados <= 0) {
			$("#enlace_anterior").hide();
		} else {
			$("#enlace_anterior").show();
		}

		$("#comienzo_resultado").html(comienzo_resultados);
		$("#total_resultados").html(totalVideojuegos);
		if ((comienzo_resultados + cantidad_paginacion) > totalVideojuegos) {
			$("#enlace_siguiente").hide();
		} else {
			$("#enlace_siguiente").show();
		}


		//Entrar en detalles de pedido
		$(".reproductor").click(function (e) {
			let id_producto = $(this).attr("id-producto");
			$.getJSON("servicioWebVideojuegos/obtenerVideojuegoDetalles",
				{
					id: id_producto
				}
			).done(function (res) {
				let html = Mustache.render(plantillaDetallesVideojuego, res);
				$('#estilo-actual').attr('href', 'css/detalles_videojuego.css');
				console.log(res);
				$("#contenedor").html(html);

				//Funcion de cambiar la cantidad
				cambioCantidad();


				//Indicamos que hace el enlace comprar
				$(".enlace_comprar_listado_principal").click(function (res) {
					if (nombre_login != "") {
						let id_producto = $(this).attr("id-producto");
						$.post("servicioWebCarrito/agregarVideojuego",
							{
								id: id_producto,
								cantidad: parseInt(document.querySelector(".cantidad").textContent)
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


function cambioCantidad() {
	document.querySelectorAll(".detalles").forEach(item => {
		let precioCantidad = item.querySelector(".precio-cantidad");
				let precio = parseInt(item.querySelector(".precio").textContent);
				precioCantidad.textContent = precio * parseInt(item.querySelector(".cantidad").textContent);



				item.querySelector(".aumentar").addEventListener("click", function () {
					let cantidad = parseInt(item.querySelector(".cantidad").textContent);
					let id_videojuego = $(this).attr("id-videojuego");
					if (cantidad >= 10) {

					} else {
						$.post("servicioWebCarrito/cambiarCantidadProducto",
							{
								id: id_videojuego,
								cantidad: cantidad + 1
							}).done(function (res) {
								if (res == "ok") {
									item.querySelector(".cantidad").textContent = cantidad + 1;
									precioCantidad.textContent = precio * ( cantidad + 1);
								} else {
									alert(res);
								}
							});
					}
				});

				item.querySelector(".restar").addEventListener("click", function () {
					let cantidad = parseInt(item.querySelector(".cantidad").textContent);
					let id_videojuego = $(this).attr("id-videojuego");
					if (cantidad <= 1) {

					} else {
						$.post("servicioWebCarrito/cambiarCantidadProducto",
							{
								id: id_videojuego,
								cantidad: cantidad - 1
							}).done(function (res) {
								if (res == "ok") {
									item.querySelector(".cantidad").textContent = cantidad - 1;
									precioCantidad.textContent = precio * ( cantidad - 1);
								} else {
									alert(res);
								}
							});
					}
				});

	});
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


function actualizarNavbar() {
	if (nombre_login != "") {
		$("#registro").hide();
		$("#inicio_sesion").hide();
		$("#carrito").show();
		$("#misdatos").show();
		$("#mispedidos").show();
		$("#logout").show();
	} else {
		$("#registro").show();
		$("#inicio_sesion").show();
		$("#carrito").hide();
		$("#misdatos").hide();
		$("#mispedidos").hide();
		$("#logout").hide();
	}
}
