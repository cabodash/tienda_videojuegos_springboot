package springboot.tienda.controllers.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springboot.tienda.model.Genero;
import springboot.tienda.services.ServicioGeneros;

@Controller
@RequestMapping("admin/")
public class GenerosController {
	
	@Autowired ServicioGeneros servicioGeneros;
	
	@RequestMapping("obtenerGeneros")
	public String obtenerGeneros(Model model) {
		model.addAttribute("generos", servicioGeneros.obtenerGeneros());
		return "admin/generos";
	}

	@RequestMapping("registrarGenero")
	public String registrarGenero(Model model) {
		Genero g = new Genero();
		model.addAttribute("nuevoGenero", g);		
		return "admin/generos_registro";
	}

	@RequestMapping("guardarGenero")
	public String guardarGenero(Genero generoNuevo, HttpServletRequest request) {
		servicioGeneros.registrarGenero(generoNuevo);
		return "admin/generos_registro_ok";
	}
	
	@RequestMapping("borrarGenero")
	public String borrarGenero(@RequestParam("id") Integer id, Model model) {
		servicioGeneros.borrarGenero(id);
		return obtenerGeneros(model);
	}
	
	@RequestMapping("editarGenero")
	public String editarGenero(@RequestParam("id") Integer id, Model model) {
		Genero genero = servicioGeneros.obtenerGeneroPorId(id);
		model.addAttribute("generoEditar", genero);
		return "admin/generos_editar";
	}
	
	@RequestMapping("guardarCambiosGenero")
	public String guardarCambiosGenero(Genero generoEditar, Model model) {
		
		servicioGeneros.guardarCambiosGenero(generoEditar);
		return obtenerGeneros(model);
	}

}
