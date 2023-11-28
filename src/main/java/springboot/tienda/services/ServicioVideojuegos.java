package springboot.tienda.services;

import java.util.List;
import java.util.Map;


import springboot.tienda.model.Videojuego;


public interface ServicioVideojuegos {

	void registrarVideojuego(Videojuego v);
	
	List<Videojuego> obtenerVideojuegos();	

	List<Videojuego> obtenerVideojuegosPorNombre(String nombre);	

	List<Videojuego> obtenerVideojuegosPorNombreComienzoFin(String nombre, int comienzo, int resultadosPorPagina);
	
	public int obtenerTotalVideojuegos (String nombre);
	
	
	void borrarVideojuego(int id);
	
	Videojuego obtenerVideojuegoPorId(int id);
	
	void guardarCambiosVideojuego(Videojuego v, List<Integer> generosSeleccionados, List<Integer> plataformasSeleccionadas);
	
	//metodos para la comunicacion por ajax
	List<Map<String, Object>> obtenerVideojuegosParaFormarJSON(String nombre, int comienzo);

	Map<String, Object> obtenerDetallesVideojuego(int idVideojuego);
	
}
