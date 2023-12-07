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

import springboot.tienda.model.Plataforma;
import springboot.tienda.services.ServicioPlataformas;

@Controller
@RequestMapping("admin/")
public class PlataformasController {
	
	@Autowired ServicioPlataformas servicioPlataformas;
	
	@RequestMapping("obtenerPlataformas")
	public String obtenerPlataformas(Model model) {
		model.addAttribute("plataformas", servicioPlataformas.obtenerPlataformas());
		return "admin/plataformas";
	}

	@RequestMapping("registrarPlataforma")
	public String registrarPlataforma(Model model) {
		Plataforma p = new Plataforma();
		model.addAttribute("nuevaPlataforma", p);		
		return "admin/plataformas_registro";
	}

	@RequestMapping("guardarPlataforma")
	public String guardarPlataforma(@ModelAttribute("nuevaPlataforma") @Valid Plataforma PlataformaNuevo, BindingResult resultadoValidacion, Model model) {
		if (resultadoValidacion.hasErrors()) {
			model.addAttribute("nuevaPlataforma", PlataformaNuevo);
			return "admin/plataformas_registro";
		}
		servicioPlataformas.registrarPlataforma(PlataformaNuevo);
		return "admin/plataformas_registro_ok";
	}
	
	@RequestMapping("borrarPlataforma")
	public String borrarPlataforma(@RequestParam("id") Integer id, Model model) {
		servicioPlataformas.borrarPlataforma(id);
		return "redirect:obtenerPlataformas";
	}
	
	@RequestMapping("editarPlataforma")
	public String editarPlataforma(@RequestParam("id") Integer id, Model model) {
		Plataforma plataforma = servicioPlataformas.obtenerPlataformaPorId(id);
		model.addAttribute("plataformaEditar", plataforma);
		return "admin/plataformas_editar";
	}
	
	@RequestMapping("guardarCambiosPlataforma")
	public String guardarCambiosPlataforma(@ModelAttribute("plataformaEditar") @Valid Plataforma PlataformaEditar, BindingResult resultadoValidacion, Model model) {
		if (resultadoValidacion.hasErrors()) {
			model.addAttribute("nuevaPlataforma", PlataformaEditar);
			return "admin/plataformas_editar";
		}
		servicioPlataformas.guardarCambiosPlataforma(PlataformaEditar);
		return "redirect:obtenerPlataformas";
	}

}
