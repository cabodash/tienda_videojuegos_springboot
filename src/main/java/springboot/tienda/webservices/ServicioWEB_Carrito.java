package springboot.tienda.webservices;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.tienda.model.Usuario;
import springboot.tienda.servicios.ServicioCarrito;


@Controller
@RestController
@RequestMapping("servicioWebCarrito/")
public class ServicioWEB_Carrito {

	@Autowired
	ServicioCarrito servicioCarrito;

	@RequestMapping("agregarVideojuego")
	public String agregarVideojuego(String id, String cantidad, HttpServletRequest request) {
		String respuesta = "";
		// esto es que el usuario se identifico correctamente
		if (request.getSession().getAttribute("usuario_identificado") != null) {
			// Recogemos el usuario de la session
			Usuario u = (Usuario) request.getSession().getAttribute("usuario_identificado");
			respuesta = "Agregar producto de id: " + id + " y Cantidad: " + cantidad + " al usuario de id: "
					+ u.getId();
			servicioCarrito.agregarProducto(u.getId(), Integer.parseInt(id), Integer.parseInt(cantidad));
		} else {
			respuesta = "Usuario no identificado, identificate para poder comprar productos";
		}

		return respuesta;
	}

	@RequestMapping("obtenerProductosCarrito")
	public List<Map<String, Object>> obtenerProductosCarrito(HttpServletRequest request) throws Exception{
		if(request.getSession().getAttribute("usuario_identificado") != null) {
			
			return servicioCarrito.obtenerProductosCarrito(
							((Usuario)request.getSession().getAttribute("usuario_identificado")).getId()
								);
		}else {
			throw new Exception("[-] -Usuario no identificado (ServicioWEB_Carrito/obtenerProtuctosCarrito)");
		}
	}
	


}
