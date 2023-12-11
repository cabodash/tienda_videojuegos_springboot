package springboot.tienda.services;

import java.util.List;
import java.util.Map;

import springboot.tienda.data.webservices.ResumenPedido;
import springboot.tienda.model.Pedido;



public interface ServicioPedidos {
	//gestion en administracion
	List<Pedido> obtenerPedidos();
	Pedido obtenerPedidoPorId(int idPedido);
	void actualizarEstadoPedido(int idPedido, String estado);
	
	
	
	//Funciones para ajax
	void procesarPaso1(String nombre, String apellidos, String direccion, String ciudad, String codigoPostal, String provincia, int idUsuario);
	void procesarPaso2(String titular, String numero, String tarjeta, String fechaCaducidad, String cvv, int idUsuario);
	void procesarPaso3(String personaContacto, String telefonoContacto, int idUsuario);
	ResumenPedido obtenerResumenDelPedido(int idUsuario);
	void confirmarPedido(int idUsuario);

	List<Map<String, Object>> obtenerProductosPedido(int idPedido);
	List<Map<String, Object>> obtenerPedidosCliente(int idUsuario);

}
