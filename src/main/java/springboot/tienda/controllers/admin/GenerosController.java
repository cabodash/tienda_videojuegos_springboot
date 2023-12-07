package springboot.tienda.controllers.admin;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String guardarGenero(@ModelAttribute("nuevoGenero") @Valid Genero generoNuevo, BindingResult resultadoValidacion, Model model) {
		if (resultadoValidacion.hasErrors()){
			model.addAttribute("nuevoGenero", generoNuevo);		
		return "admin/generos_registro";
		}
		servicioGeneros.registrarGenero(generoNuevo);
		return "admin/generos_registro_ok";
	}
	
	@RequestMapping("borrarGenero")
	public String borrarGenero(@RequestParam("id") Integer id, Model model) {
		servicioGeneros.borrarGenero(id);
		return "redirect:obtenerGeneros";
	}
	
	@RequestMapping("editarGenero")
	public String editarGenero(@RequestParam("id") Integer id, Model model) {
		Genero genero = servicioGeneros.obtenerGeneroPorId(id);
		model.addAttribute("generoEditar", genero);
		return "admin/generos_editar";
	}
	
	@RequestMapping("guardarCambiosGenero")
	public String guardarCambiosGenero(@ModelAttribute("generoEditar") @Valid Genero generoEditar,  BindingResult resultadoValidacion, Model model) {
		if (resultadoValidacion.hasErrors()){
			model.addAttribute("nuevoGenero", generoEditar);		
		return "admin/generos_editar";
		}
		servicioGeneros.guardarCambiosGenero(generoEditar);
		return "redirect:obtenerGeneros";
	}

}
