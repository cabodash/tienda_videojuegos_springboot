let plantillaPedidos = "";
let plantillaProductoPedido = "";

$.get("plantillas_mustache/pedidos.html", (data) => {
	plantillaPedidos = data;
});
$.get("plantillas_mustache/producto_pedido.html", (data) => {
	plantillaProductoPedido = data;
});

$("#mispedidos").click(mostrar_pedidos);

function mostrar_pedidos() {
	let texto_html = "";
	if (nombre_login != "") {
		$.getJSON("servicioWebPedidos/obtenerPedidos", function (res) {
			console.log(res);
			texto_html = Mustache.render(plantillaPedidos, res);
			$("#contenedor").html(texto_html);
		}).fail(function () {
			alert("Ocurrió un error al cargar los pedidos");
		}).done(function () {
            document.querySelectorAll(".item_pedido").forEach(function (item){
                let id_pedido = item.getAttribute("id-pedido");
                $.getJSON("servicioWebPedidos/obtenerProductosPedido?id_pedido=" + id_pedido, function (res) {
                    console.log(res);
                    texto_html = Mustache.render(plantillaProductoPedido, res);
                    $(item).find(".contenedor-productos").each(function() {
                        $(this).html(texto_html);
                    });
                });
            });
        });
        
	} else {
		alert("Debes iniciar sesión para ver tus pedidos");
		iniciar_sesion();
	}
};
