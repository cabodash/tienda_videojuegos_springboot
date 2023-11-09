package springboot.tienda.utilidades;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import springboot.tienda.model.Usuario;
import springboot.tienda.model.Videojuego;



@Component
public class GestorArchivos {
	public static void guardarPortadaVideojuego(Videojuego v, String rutaReal) {
		MultipartFile archivo = v.getFotoSubida();
		String nombreArchivo = v.getId() + ".jpg";
		
		
		String rutaSubidas = rutaReal + "subidas";
		File fileRutaSubidas = new File(rutaSubidas);
		if(!fileRutaSubidas.exists()) {
			fileRutaSubidas.mkdirs();
		}
		
		if(archivo.getSize()>0){
			try {
				archivo.transferTo(new File((rutaSubidas), nombreArchivo));
				System.out.println("Archivo en: " + rutaSubidas);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				System.out.println("[-] No se pudo mover el archivo a la ruta de subidas");
				e.printStackTrace();
			}
		}else {
			System.out.println("[i] Se registro un producto sin imagen");
		}
	}
	
	
	public static void guardarAvatarUsuario(Usuario u, String rutaReal, MultipartFile avatar) {
		String nombreArchivo = u.getId() + ".jpg";
		File fileCarpetaAvatares = new File(rutaReal+"subidas_usuarios");
		if(!fileCarpetaAvatares.exists()) {
			fileCarpetaAvatares.mkdirs();
			
		}
		if(avatar.getSize()>0) {
			try {
				avatar.transferTo(new File(rutaReal + "subidas_usuarios",nombreArchivo));
				System.out.println("Avatar de usuario en: " + rutaReal);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
