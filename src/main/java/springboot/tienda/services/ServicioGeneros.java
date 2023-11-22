package springboot.tienda.services;

import java.util.List;
import java.util.Map;

import springboot.tienda.model.Genero;

public interface ServicioGeneros {
	Map<String, String> obtenerGenerosParaDesplegable();
	List<Genero> obtenerGeneros();
	
	
	void registrarGenero(Genero g);	
	void borrarGenero(int id);
	Genero obtenerGeneroPorId(int id);
	void guardarCambiosGenero(Genero g);
	
	

}
