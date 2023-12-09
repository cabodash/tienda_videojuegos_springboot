package springboot.tienda.webservices;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springboot.tienda.data.webservices.InfoVideojuegos;
import springboot.tienda.services.ServicioVideojuegos;


@Controller
@RestController
@RequestMapping("servicioWebVideojuegos/")
public class ServicioWEB_Videojuegos {

	//Pedir bean de spring cuyo nombre se clase se usa en varios paquetes
	//webservices.admin.ServicioWEB_Pedidos
	//usando @Qualifiyer

	// @Autowired
	// @Qualifier("servicioWebPedidosAdmin")
	// private ServicioWEB_Pedidos servicioWEBPedidos;

	
	@Autowired
	private ServicioVideojuegos servicioVideojuegos;
	
	@RequestMapping("obtenerVideojuegos")
	public InfoVideojuegos obtenerVideojuegos(@RequestParam(name = "dato", defaultValue = "") String dato, @RequestParam(name = "comienzo", defaultValue = "0") Integer comienzo){
		InfoVideojuegos info = new InfoVideojuegos();
		info.setVideojuegos(servicioVideojuegos.obtenerVideojuegosParaFormarJSON(dato, comienzo));
		info.setTotalVideojuegos(servicioVideojuegos.obtenerTotalVideojuegos(dato));
		return info;
	}
	
	@RequestMapping("obtenerVideojuegoDetalles")
	public Map<String,Object> obtenerVideojuegoDetalles(@RequestParam("id") Integer id){
		return servicioVideojuegos.obtenerDetallesVideojuego(id);
	}
	

}
