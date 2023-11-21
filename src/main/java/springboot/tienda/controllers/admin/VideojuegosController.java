package springboot.tienda.controllers.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springboot.tienda.model.Videojuego;
import springboot.tienda.servicios.ServicioGeneros;
import springboot.tienda.servicios.ServicioVideojuegos;

@Controller
@RequestMapping("admin/")
public class VideojuegosController {

	@Autowired
	private ServicioVideojuegos servicioVideojuegos;
	
	@Autowired
	private ServicioGeneros servicioGeneros;

	@RequestMapping("obtenerVideojuegos")
	public String obtenerVideojuegos(@RequestParam(name = "buscador", defaultValue = "") String nombre, Model model) {
		model.addAttribute("videojuegos", servicioVideojuegos.obtenerVideojuegosPorNombre(nombre));
		model.addAttribute("nombre", nombre);
		return "admin/videojuegos";
	}

	@RequestMapping("registrarVideojuego")
	public String registrarVideojuego(Model model) {
		Videojuego v = new Videojuego();
		v.setPrecio(1);
		model.addAttribute("nuevoVideojuego", v);
		model.addAttribute("generos", servicioGeneros.obtenerGeneros());
		
		
		return "admin/videojuegos_registro";
	}

	@RequestMapping("guardarVideojuego")
	public String guardarVideojuego(Videojuego videojuegoNuevo, HttpServletRequest request) {
		System.out.println("Id de categoria: " + videojuegoNuevo.getIdGenero());
		servicioVideojuegos.registrarVideojuego(videojuegoNuevo);
		
		
		return "admin/videojuegos_registro_ok";
	}
	
	@RequestMapping("borrarVideojuego")
	public String borrarVideojuego(@RequestParam("id") Integer id, Model model) {
		servicioVideojuegos.borrarVideojuego(id);
		return obtenerVideojuegos("", model);
	}
	
	@RequestMapping("editarVideojuego")
	public String editarVideojuego(@RequestParam("id") Integer id, Model model) {
		Videojuego videojuego = servicioVideojuegos.obtenerVideojuegoPorId(id);
		videojuego.setIdGenero(videojuego.getGenero().getId());
		model.addAttribute("videojuegoEditar", videojuego);
		model.addAttribute("generos", servicioGeneros.obtenerGeneros());
		return "admin/videojuegos_editar";
	}
	
	@RequestMapping("guardarCambiosVideojuego")
	public String guardarCambiosVideojuego(Videojuego videojuegoEditar, Model model) {
		
		servicioVideojuegos.guardarCambiosVideojuego(videojuegoEditar);
		return obtenerVideojuegos("", model);
	}

}
