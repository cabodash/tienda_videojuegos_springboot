package springboot.tienda.services;

import java.util.List;
import java.util.Map;

import springboot.tienda.model.Plataforma;

public interface ServicioPlataformas {
	Map<String, String> obtenerPlataformasParaDesplegable();
	List<Plataforma> obtenerPlataformas();
	
	
	void registrarPlataforma(Plataforma g);	
	void borrarPlataforma(int id);
	Plataforma obtenerPlataformaPorId(int id);
	List <Plataforma> obtenerPlataformasPorIdVideojuego(int idVideojuego);
	void guardarCambiosPlataforma(Plataforma g);
	
	

}
