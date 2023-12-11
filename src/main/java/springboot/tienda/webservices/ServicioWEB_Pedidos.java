package springboot.tienda.webservices;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springboot.tienda.data.webservices.ResumenPedido;
import springboot.tienda.model.Pedido;
import springboot.tienda.model.ProductoPedido;
import springboot.tienda.model.Usuario;
import springboot.tienda.services.ServicioPedidos;


@Controller
@RestController
@RequestMapping("servicioWebPedidos/")
public class ServicioWEB_Pedidos {
	
	@Autowired
	ServicioPedidos servicioPedidos;

	@RequestMapping("paso1")
	public ResponseEntity<String> paso1(String nombre, String apellidos, String direccion, String provincia, String ciudad, String codigoPostal, HttpServletRequest request){
		String respuesta = "";
		Usuario u = (Usuario)request.getSession().getAttribute("usuario_identificado");
		servicioPedidos.procesarPaso1(nombre, apellidos, direccion, ciudad, codigoPostal, provincia, u.getId());
		respuesta = "ok";
		
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}
	
	@RequestMapping("paso2")
	public ResponseEntity<String> paso2(String titular, String numero, String tarjeta, String fechaCaducidad, String cvv, HttpServletRequest request){
		String respuesta = "";
		Usuario u = (Usuario)request.getSession().getAttribute("usuario_identificado");
		servicioPedidos.procesarPaso2(titular, numero, tarjeta, fechaCaducidad, cvv, u.getId());;
		respuesta = "ok";
		
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}
	
	@RequestMapping("paso3")
	public ResumenPedido paso3( String personaContacto, String telefonoContacto, HttpServletRequest request){
		Usuario u = (Usuario)request.getSession().getAttribute("usuario_identificado");
		servicioPedidos.procesarPaso3(personaContacto, telefonoContacto, u.getId());;
		ResumenPedido resumen = servicioPedidos.obtenerResumenDelPedido(u.getId());
	
		return resumen;
	}
	
	@RequestMapping("paso4")
	public ResponseEntity<String> paso4(HttpServletRequest request){
		String respuesta = "";
		Usuario u = (Usuario)request.getSession().getAttribute("usuario_identificado");
		servicioPedidos.confirmarPedido(u.getId());
		respuesta = "pedido completado";
		
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}

	@RequestMapping("obtenerPedidos")
	public List<Map<String, Object>> obtenerPedidos(HttpServletRequest requert) throws Exception {
		if (requert.getSession().getAttribute("usuario_identificado") != null) {
			int idUsuario = ((Usuario) requert.getSession().getAttribute("usuario_identificado")).getId();
			return servicioPedidos.obtenerPedidosCliente(idUsuario);
		} else {
			throw new Exception("usuario no identificado");
		}
	}
	
	//cambiar para que devuelva un List<Map<String, Object>>//
	@RequestMapping("obtenerProductosPedido")
	public List<Map<String, Object>> obtenerProductosPedido(@RequestParam("id_pedido") Integer id_pedido, HttpServletRequest requert) throws Exception {
		if (requert.getSession().getAttribute("usuario_identificado") != null) {
			return servicioPedidos.obtenerProductosPedido(id_pedido);
		} else {
			throw new Exception("usuario no identificado");
		}
	}
	
	
}
