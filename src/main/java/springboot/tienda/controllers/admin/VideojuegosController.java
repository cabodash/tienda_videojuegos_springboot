package springboot.tienda.controllers.admin;

import java.util.HashSet;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springboot.tienda.model.Genero;
import springboot.tienda.model.Plataforma;
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
	public String guardarVideojuego(@Valid Videojuego videojuegoNuevo, @RequestParam List<Integer> generosSeleccionados, @RequestParam List<Integer> plataformasSeleccionadas, BindingResult resultadoValidacion) {
		// if (resultadoValidacion.hasErrors()) {
		// 	return "admin/videojuegos_registro";	
		// }
		videojuegoNuevo.setGeneros(new HashSet<>(servicioGeneros.obtenerGenerosPorIds(generosSeleccionados)));
		videojuegoNuevo.setPlataformas(servicioPlataformas.obtenerPlataformasPorIds(plataformasSeleccionadas));
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
		if (videojuego.getPlataformas() == null || videojuego.getPlataformas().isEmpty()) {
			System.out.println("1vacio");
		}else{
			for (Plataforma plataforma : videojuego.getPlataformas()) {
				System.out.println(plataforma.getNombre());
			}
		}
		if (videojuego.getGeneros() == null || videojuego.getGeneros().isEmpty()) {
			System.out.println("2vacio");
		}else{
			for (Genero genero : videojuego.getGeneros()) {
				System.out.println(genero.getNombre());
			}
		}
		model.addAttribute("videojuegoEditar", videojuego);
		model.addAttribute("generos", servicioGeneros.obtenerGeneros());
		model.addAttribute("plataformas", servicioPlataformas.obtenerPlataformas());
		return "admin/videojuegos_editar";
	}
	
	@RequestMapping("guardarCambiosVideojuego")
	public String guardarCambiosVideojuego(Videojuego videojuegoEditar, @RequestParam List<Integer> generosSeleccionados, @RequestParam List<Integer> plataformasSeleccionadas, Model model) {
		servicioVideojuegos.guardarCambiosVideojuego(videojuegoEditar, generosSeleccionados, plataformasSeleccionadas);
		return obtenerVideojuegos("",0, model);
	}

}