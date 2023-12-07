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

import springboot.tienda.model.Usuario;
import springboot.tienda.services.ServicioUsuarios;

@Controller
@RequestMapping("admin/")
public class UsuariosController {
	
	@Autowired ServicioUsuarios servicioUsuarios;
	
	@RequestMapping("obtenerUsuarios")
	public String obtenerUsuarios(Model model) {
		model.addAttribute("usuarios", servicioUsuarios.obtenerUsuarios());
		return "admin/usuarios";
	}

	@RequestMapping("registrarUsuario")
	public String registrarUsuario(Model model) {
		Usuario u = new Usuario();
		model.addAttribute("nuevoUsuario", u);		
		return "admin/usuarios_registro";
	}

	@RequestMapping("guardarUsuario")
	public String guardarVideojuego(@ModelAttribute("usuarioNuevo") @Valid Usuario usuarioNuevo, BindingResult resultadoValidacion, Model model) {
		if (resultadoValidacion.hasErrors()) {
			model.addAttribute("nuevoUsuario", usuarioNuevo);
			return "admin/usuarios_registro";	
		}
		servicioUsuarios.registrarUsuario(usuarioNuevo);
		return "admin/usuarios_registro_ok";
	}
	
	@RequestMapping("borrarUsuario")
	public String borrarUsuario(@RequestParam("id") Integer id, Model model) {
		servicioUsuarios.borrarUsuario(id);
		return "redirect:obtenerUsuarios";
	}
	
	@RequestMapping("editarUsuario")
	public String editarUsuario(@RequestParam("id") Integer id, Model model) {
		Usuario usuario = servicioUsuarios.obtenerUsuarioPorId(id);
		model.addAttribute("usuarioEditar", usuario);
		return "admin/usuarios_editar";
	}
	
	@RequestMapping("guardarCambiosUsuario")
	public String guardarCambiosUsuario(@ModelAttribute("usuarioEditar") @Valid Usuario usuarioEditar, BindingResult resultadoValidacion,  Model model) {
		if (resultadoValidacion.hasErrors()) {
			model.addAttribute("usuarioEditar", usuarioEditar);
			return "admin/usuarios_editar";	
		}
		servicioUsuarios.guardarCambiosUsuario(usuarioEditar);
		return "redirect:obtenerUsuarios";
	}

}
