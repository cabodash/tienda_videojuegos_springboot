package springboot.tienda.services;

import java.util.List;
import java.util.Map;

import springboot.tienda.model.Plataforma;

public interface ServicioPlataformas {
	Map<String, String> obtenerPlataformasParaDesplegable();
	List<Plataforma> obtenerPlataformas();
	
	
	void registrarPlataforma(Plataforma g);	
	void bajaPlataforma(int id);
	void altaPlataforma(int id);
	void borrarPlataformasVideojuegoPorIdVideojuego(int idVideojuego);
	Plataforma obtenerPlataformaPorId(int id);
	List<Plataforma> obtenerPlataformasPorIds(List<Integer> ids);
	List <Plataforma> obtenerPlataformasPorIdVideojuego(int idVideojuego);
	List<Map<String, Object>> obtenerPlataformasParaJSON(int idVideojuego);
	void guardarCambiosPlataforma(Plataforma g);
	
	

}
