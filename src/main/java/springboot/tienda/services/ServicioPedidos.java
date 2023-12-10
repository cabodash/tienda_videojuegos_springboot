package springboot.tienda.services;

import java.util.List;

import springboot.tienda.data.webservices.ResumenPedido;
import springboot.tienda.model.Pedido;
import springboot.tienda.model.ProductoPedido;



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

	List<ProductoPedido> obtenerProductosPedido(int idPedido);
	List<Pedido> obtenerPedidosCliente(int idUsuario);

}
