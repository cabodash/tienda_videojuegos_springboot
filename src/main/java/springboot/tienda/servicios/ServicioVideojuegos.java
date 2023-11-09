package springboot.tienda.servicios;

import java.util.List;
import java.util.Map;

import springboot.tienda.model.Videojuego;


public interface ServicioVideojuegos {

	void registrarVideojuego(Videojuego v);
	
	List<Videojuego> obtenerVideojuegos();	
	
	void borrarVideojuego(int id);
	
	Videojuego obtenerVideojuegoPorId(int id);
	
	void guardarCambiosVideojuego(Videojuego v);
	
	//metodos para la comunicacion por ajax
	List<Map<String, Object>> obtenerVideojuegosParaFormarJSON();

	Map<String, Object> obtenerDetallesVideojuego(int idVideojuego);
	
}
