package springboot.tienda.controllers.admin;

import java.util.HashSet;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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

		model.addAttribute("videojuegos", servicioVideojuegos.obtenerVideojuegosDatoPaginado(nombre, comienzo, 10));
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
	public String guardarVideojuego(@ModelAttribute("nuevoVideojuego") @Valid Videojuego nuevoVideojuego, 
									BindingResult resultadoValidacion, 
									@RequestParam(required = false) List<Integer> generosSeleccionados, 
									@RequestParam(required = false) List<Integer> plataformasSeleccionadas, 
									Model model) {
										
		if (resultadoValidacion.hasErrors()) {
			//Comprobacion de que no mande un null directo
			if(generosSeleccionados != null && !generosSeleccionados.isEmpty()){
				nuevoVideojuego.setGeneros(new HashSet<>(servicioGeneros.obtenerGenerosPorIds(generosSeleccionados)));
			}
			if(plataformasSeleccionadas != null && !plataformasSeleccionadas.isEmpty()){
				nuevoVideojuego.setPlataformas(servicioPlataformas.obtenerPlataformasPorIds(plataformasSeleccionadas));
			}
			model.addAttribute("nuevoVideojuego", nuevoVideojuego);
			model.addAttribute("generos", servicioGeneros.obtenerGeneros());
			model.addAttribute("plataformas", servicioPlataformas.obtenerPlataformas());
			return "admin/videojuegos_registro";	
		}
		nuevoVideojuego.setGeneros(new HashSet<>(servicioGeneros.obtenerGenerosPorIds(generosSeleccionados)));
		nuevoVideojuego.setPlataformas(servicioPlataformas.obtenerPlataformasPorIds(plataformasSeleccionadas));
		servicioVideojuegos.registrarVideojuego(nuevoVideojuego);
		return "admin/videojuegos_registro_ok";
	}
	
	@RequestMapping("bajaVideojuego")
	public String bajaVideojuego(@RequestParam("id") Integer id, Model model) {
		servicioVideojuegos.bajaVideojuego(id);
		return "redirect:obtenerVideojuegos";
	}

	@RequestMapping("altaVideojuego")
	public String altaVideojuego(@RequestParam("id") Integer id, Model model) {
		servicioVideojuegos.altaVideojuego(id);
		return "redirect:obtenerVideojuegos";
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
	public String guardarCambiosVideojuego(@ModelAttribute("videojuegoEditar") @Valid Videojuego videojuegoEditar, 
									BindingResult resultadoValidacion, 
									@RequestParam(required = false) List<Integer> generosSeleccionados, 
									@RequestParam(required = false) List<Integer> plataformasSeleccionadas, 
									Model model) {

		if (resultadoValidacion.hasErrors()) {
			videojuegoEditar.setGeneros(new HashSet<>(servicioGeneros.obtenerGenerosPorIds(generosSeleccionados)));
			videojuegoEditar.setPlataformas(servicioPlataformas.obtenerPlataformasPorIds(plataformasSeleccionadas));
			model.addAttribute("videojuegoEditar", videojuegoEditar);
			model.addAttribute("generos", servicioGeneros.obtenerGeneros());
			model.addAttribute("plataformas", servicioPlataformas.obtenerPlataformas());
			model.addAttribute("generosSeleccionados", generosSeleccionados);
			model.addAttribute("plataformasSeleccionadas", plataformasSeleccionadas);
			return "admin/videojuegos_editar";	
		}
		servicioVideojuegos.guardarCambiosVideojuego(videojuegoEditar, generosSeleccionados, plataformasSeleccionadas);
		return "redirect:obtenerVideojuegos";
	}

}