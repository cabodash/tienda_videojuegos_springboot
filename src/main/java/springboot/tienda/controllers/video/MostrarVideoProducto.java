package springboot.tienda.controllers.video;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springboot.tienda.servicios.ServicioVideojuegos;

@Controller
public class MostrarVideoProducto {
    
    @Autowired
    private ServicioVideojuegos servicioVideojuegos;

    @RequestMapping("mostrar_video_videojuego")
    public void mostrar_video_videojuego(@RequestParam("id") Integer id, HttpServletResponse response) throws IOException{ 
		byte[] info = servicioVideojuegos.obtenerVideojuegoPorId(id).getVideoPortada();
		if (info == null) {
			return;
		}
		response.setContentType("video/webm, video/mp4, video/ogg");
		response.getOutputStream().write(info);
		response.getOutputStream().close();
	}
}
