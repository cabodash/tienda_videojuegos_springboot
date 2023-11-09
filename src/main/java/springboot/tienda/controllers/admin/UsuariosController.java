package springboot.tienda.controllers.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springboot.tienda.model.Usuario;
import springboot.tienda.servicios.ServicioUsuarios;

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
		Usuario g = new Usuario();
		model.addAttribute("nuevoUsuario", g);		
		return "admin/usuarios_registro";
	}

	@RequestMapping("guardarUsuario")
	public String guardarVideojuego(Usuario usuarioNuevo, HttpServletRequest request) {
		servicioUsuarios.registrarUsuario(usuarioNuevo);
		return "admin/usuarios_registro_ok";
	}
	
	@RequestMapping("borrarUsuario")
	public String borrarUsuario(@RequestParam("id") Integer id, Model model) {
		servicioUsuarios.borrarUsuario(id);
		return obtenerUsuarios(model);
	}
	
	@RequestMapping("editarUsuario")
	public String editarUsuario(@RequestParam("id") Integer id, Model model) {
		Usuario usuario = servicioUsuarios.obtenerUsuarioPorId(id);
		model.addAttribute("usuarioEditar", usuario);
		return "admin/usuarios_editar";
	}
	
	@RequestMapping("guardarCambiosUsuario")
	public String guardarCambiosUsuario(Usuario usuarioEditar, Model model) {
		
		servicioUsuarios.guardarCambiosUsuario(usuarioEditar);
		return obtenerUsuarios(model);
	}

}
