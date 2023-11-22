package springboot.tienda.services;

import java.util.List;
import java.util.Map;

public interface ServicioCarrito {
	void agregarProducto(int idUsuario, int idProducto, int cantidad);
	void actualizarProductoCarrito(int idUsuario, int idProducto, int cantidad);
	void borrarCarrito(int idUsuario, int idProducto);
	
	
	
	//Operaciones para ajax
	List<Map<String, Object>> obtenerProductosCarrito(int idUsuario);
	
}
