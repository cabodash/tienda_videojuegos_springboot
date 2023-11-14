package springboot.tienda.webservices;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springboot.tienda.servicios.ServicioVideojuegos;


@Controller
@RestController
@RequestMapping("servicioWebVideojuegos/")
public class ServicioWEB_Videojuegos {
	
	@Autowired
	private ServicioVideojuegos servicioVideojuegos;
	
	@RequestMapping("obtenerVideojuegos")
	public List<Map<String, Object>> obtenerVideojuegos(){
		return servicioVideojuegos.obtenerVideojuegosParaFormarJSON();
	}
	
	@RequestMapping("obtenerVideojuegoDetalles")
	public Map<String,Object> obtenerVideojuegoDetalles(@RequestParam("id") Integer id){
		return servicioVideojuegos.obtenerDetallesVideojuego(id);
	}
	

}
