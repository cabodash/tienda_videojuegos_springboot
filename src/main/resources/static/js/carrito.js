let plantillaCarrito = "";
let plantillaCheckout_1 = "";
let plantillaCheckout_2 = "";
let plantillaCheckout_3 = "";
let plantillaCheckout_4 = "";

$.get("plantillas_mustache/carrito.html", function (data) {
	plantillaCarrito = data;
});

$.get("plantillas_mustache/checkout_1.html", function (data) {
	plantillaCheckout_1 = data;
});

$.get("plantillas_mustache/checkout_2.html", function (data) {
	plantillaCheckout_2 = data;
});

$.get("plantillas_mustache/checkout_3.html", function (data) {
	plantillaCheckout_3 = data;
});
$.get("plantillas_mustache/checkout_4.html", function (data) {
	plantillaCheckout_4 = data;
});

//Carrito
$("#carrito").click(function () {
	$('#estilo-actual').attr('href', 'css/carrito.css');
	if (nombre_login != "") {
		$.getJSON("servicioWebCarrito/obtenerProductosCarrito", res => {
			if (res == null) {
				alert("Aun no tienes productos en el carrito");
			} else {
				let html = Mustache.render(plantillaCarrito, res);
				$("#contenedor").html(html);
				$(".borrrar_producto_carrito").click(function (e) {
					e.preventDefault();
					let id_videojuego = $(this).attr("id-videojuego");
					$.post("servicioWebCarrito/borrarProducto",
						{
							id: id_videojuego
						}).done(function (res) {
							if (res == "ok") {
								$("#div-producto-" + id_videojuego).hide();
							} else {
								alert(res);
							}
						});
				});

				//hacemos click en realizar pedido
				$("#realizar_pedido").click(checkout_paso_0);
			}

		}).fail(function () {
			alert("Error, prueba a volver a identificarte");
		}).done(function () {
			document.querySelectorAll(".item-carrito").forEach(item => {
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
									item.querySelector(".cantidad-juegos").textContent = cantidad + 1;
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
									item.querySelector(".cantidad-juegos").textContent = cantidad - 1;
									precioCantidad.textContent = precio * ( cantidad - 1);
								} else {
									alert(res);
								}
							});
					}
				});

			});

		});




	} else {
		alert("Debes identificarte para acceder al carrito");
	}

});


//Funciones de los pasos del checkout//
function checkout_paso_0() {
	$("#contenedor").html(plantillaCheckout_1);
	$("#aceptar_paso_1").click(checkout_paso_1_aceptar);

}

//recoger los valores del form y mandarlos al servidor
function checkout_paso_1_aceptar() {
	let nombre = $("#campo_nombre").val();
	let apellidos = $("#campo_apellidos").val();
	let direccion = $("#campo_direccion").val();
	let ciudad = $("#campo_ciudad").val();
	let codigoPostal = $("#campo_codigo_postal").val();
	let provincia = $("#campo_provincia").val();


	/* -TODO: validar campos del formulario */

	$.post("servicioWebPedidos/paso1",
		{
			nombre: nombre,
			apellidos: apellidos,
			direccion: direccion,
			ciudad: ciudad,
			codigoPostal: codigoPostal,
			provincia: provincia,
		}).done(function (res) {
			if (res == "ok") {
				$("#contenedor").html(plantillaCheckout_2);
				$("#aceptar_paso_2").click(checkout_paso_2_aceptar);
			} else {
				alert(res);
			}
		});
}

function checkout_paso_2_aceptar() {
	let tipo_tarjeta = $("#tipo_tarjeta").find(":selected").val();
	let numero_tarjeta = $("#numero_tarjeta").val();
	let titular_tarjeta = $("#titular_tarjeta").val();
	let fecha_caducidad = $("#fecha_caducidad").val();
	let cvv_tarjeta = $("#cvv_tarjeta").val();

	$.post("servicioWebPedidos/paso2",
		{
			tarjeta: tipo_tarjeta,
			numero: numero_tarjeta,
			titular: titular_tarjeta,
			fechaCaducidad: fecha_caducidad,
			cvv: cvv_tarjeta
		}).done(function (res) {
			//Al no hacer el $.getJSON debo parsear lo recibido
			if (res == "ok") {
				$("#contenedor").html(plantillaCheckout_3);
				$("#aceptar_paso_3").click(checkout_paso_3_aceptar);
			} else {
				alert(res);
			}
		});

}

function checkout_paso_3_aceptar() {
	let persona_contacto = $("#persona_contacto").val();
	let telefono_contacto = $("#telefono_contacto").val();

	$.post("servicioWebPedidos/paso3",
		{
			personaContacto: persona_contacto,
			telefonoContacto: telefono_contacto

		}).done(function (res) {
			//con springboot nos llega en JSON ya parseado
			let html = Mustache.render(plantillaCheckout_4, res);
			$("#contenedor").html(html);
			$("#btn_confirmar_pedido").click(function () {
				$.ajax("servicioWebPedidos/paso4", {
					sucess: function (res) {
						alert("respuesta del servicio web: " + res);
					}
				});
			});
		});




}