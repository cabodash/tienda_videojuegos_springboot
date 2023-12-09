package springboot.tienda.services;

import java.util.List;
import java.util.Map;

import springboot.tienda.model.Genero;

public interface ServicioGeneros {
	List<Genero> obtenerGeneros();
	
	
	void registrarGenero(Genero g);	
	void bajaGenero(int id);
	void altaGenero(int id);
	void borrarGenerosVideojuegoPorIdVideojuego(int idVideojuego);
	Genero obtenerGeneroPorId(int id);
	List<Genero> obtenerGenerosPorIds(List<Integer> ids);
	List <Genero> obtenerGenerosPorIdVideojuego(int idVideojuego);
	List<Map<String, Object>> obtenerGenerosParaJSON(int idVideojuego);
	void guardarCambiosGenero(Genero g);
	
	

}
