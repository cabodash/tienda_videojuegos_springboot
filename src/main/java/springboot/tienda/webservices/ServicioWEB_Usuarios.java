package springboot.tienda.webservices;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import springboot.tienda.model.Usuario;
import springboot.tienda.servicios.ServicioUsuarios;


@Controller
@RestController
@RequestMapping("servicioWEB_Usuarios/")
public class ServicioWEB_Usuarios {

	@Autowired
	private ServicioUsuarios servicioUsuarios;

	@RequestMapping("registrarUsuario")
	public String registrarUsuario(
			@RequestParam Map<String, Object> formData,
			@RequestParam("avatar") CommonsMultipartFile foto, HttpServletRequest request ){
		
		System.out.println("recibido formData: " + formData);
		System.out.println("recibido foto: " + foto);
		
		//obtener un objeto de Usuario a partir de un form data
		Gson gson = new Gson();
		JsonElement json = gson.toJsonTree(formData);
		Usuario u = gson.fromJson(json, Usuario.class);
		
		servicioUsuarios.registrarUsuario(u);
		
		String rutaRealDelProyecto = request.getServletContext().getRealPath("");
		
		//GestorArchivos.guardarAvatarUsuario(u, rutaRealDelProyecto, foto);		
		
		return "usuario registrado, ya puedes identificarte";
	}

	@RequestMapping("identificarUsuario")
	public ResponseEntity<String> identificarUsuario(String email, String pass, HttpServletRequest request) {
		Usuario u = (Usuario)servicioUsuarios.obtenerUsuarioPorEmailYpass(email, pass);
		String respuesta = "";
		if (u != null) {
			request.getSession().setAttribute("usuario_identificado", u);;
			respuesta= "ok," + u.getNombre();
		}else {
			respuesta = "Usuario o contraseña incorrectas";
		}
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}
	
	@RequestMapping("logout")
	public ResponseEntity<String> logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	

}
