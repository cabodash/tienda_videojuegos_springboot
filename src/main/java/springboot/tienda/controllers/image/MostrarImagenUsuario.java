package springboot.tienda.controllers.image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springboot.tienda.services.ServicioUsuarios;

@Controller
public class MostrarImagenUsuario {
	
	@Autowired
	private ServicioUsuarios servicioUsuarios;
	
	@RequestMapping("mostrar_imagen_usuario")
	public void mostrar_imagen_usuario(@RequestParam("id") Integer id, HttpServletResponse response) throws IOException{ // en springboot puedes marcar el id como integer
		byte[] info = servicioUsuarios.obtenerUsuarioPorId(id).getImagenPerfil();
		if (info == null) {
			info = Files.readAllBytes(Paths.get("src/main/resources/static/images/default_profile.png"));
		}
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(info);
		response.getOutputStream().close();
	}

}
