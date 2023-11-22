package springboot.tienda.webservices.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springboot.tienda.services.ServicioPedidos;

//Value indica el nombre del objeto que va a gestionar spring de esta clase
//Si hay dos clases con el mismo nombre en cualquier anotacion de spring estamos
//obligados a cambiar el nombre de la referencia a spring con value
@Controller(value = "servicioWebPedidosAdmin")
@RestController
@RequestMapping("admin/servicioWebPedidos")
public class ServicioWEB_Pedidos {

	@Autowired
	private ServicioPedidos servicioPedidos;
	
	@RequestMapping("actualizarEstadoPedido")
	public String actualizarEstadoPedido(@RequestParam("id") Integer id, String estado) {
		servicioPedidos.actualizarEstadoPedido(id, estado);
		return "ok";
	}
}
