package springboot.tienda.controllers.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springboot.tienda.model.Videojuego;
import springboot.tienda.services.ServicioGeneros;
import springboot.tienda.services.ServicioPlataformas;
import springboot.tienda.services.ServicioVideojuegos;

@Controller
@RequestMapping("admin/")
public class VideojuegosController {

	@Autowired
	private ServicioVideojuegos servicioVideojuegos;
	
	@Autowired
	private ServicioGeneros servicioGeneros;

	@Autowired
	private ServicioPlataformas servicioPlataformas;

	@RequestMapping("obtenerVideojuegos")
	public String obtenerVideojuegos(@RequestParam(name = "buscador", defaultValue = "") String nombre,@RequestParam(name = "comienzo", defaultValue = "0") Integer comienzo, Model model) {

		model.addAttribute("videojuegos", servicioVideojuegos.obtenerVideojuegosPorNombreComienzoFin(nombre, comienzo, 10));
		model.addAttribute("nombre", nombre);
		model.addAttribute("siguiente", comienzo + 10);
		model.addAttribute("anterior", comienzo -10);
		model.addAttribute("total", servicioVideojuegos.obtenerTotalVideojuegos(nombre));
		return "admin/videojuegos";
	}

	@RequestMapping("registrarVideojuego")
	public String registrarVideojuego(Model model) {
		Videojuego v = new Videojuego();
		v.setPrecio(1);
		model.addAttribute("nuevoVideojuego", v);
		model.addAttribute("generos", servicioGeneros.obtenerGeneros());
		model.addAttribute("plataformas", servicioPlataformas.obtenerPlataformas());
		
		
		return "admin/videojuegos_registro";
	}

	@RequestMapping("guardarVideojuego")
	public String guardarVideojuego(@Valid Videojuego videojuegoNuevo, BindingResult resultadoValidacion) {
		// if (resultadoValidacion.hasErrors()) {
		// 	return "admin/videojuegos_registro";	
		// }
		servicioVideojuegos.registrarVideojuego(videojuegoNuevo);
		return "admin/videojuegos_registro_ok";
	}
	
	@RequestMapping("borrarVideojuego")
	public String borrarVideojuego(@RequestParam("id") Integer id, Model model) {
		servicioVideojuegos.borrarVideojuego(id);
		return obtenerVideojuegos("",0, model);
	}
	
	@RequestMapping("editarVideojuego")
	public String editarVideojuego(@RequestParam("id") Integer id, Model model) {
		Videojuego videojuego = servicioVideojuegos.obtenerVideojuegoPorId(id);
		model.addAttribute("videojuegoEditar", videojuego);
		model.addAttribute("generos", servicioGeneros.obtenerGeneros());
		model.addAttribute("plataformas", servicioPlataformas.obtenerPlataformas());
		return "admin/videojuegos_editar";
	}
	
	@RequestMapping("guardarCambiosVideojuego")
	public String guardarCambiosVideojuego(Videojuego videojuegoEditar, Model model) {
		
		servicioVideojuegos.guardarCambiosVideojuego(videojuegoEditar);
		return obtenerVideojuegos("",0, model);
	}

}
