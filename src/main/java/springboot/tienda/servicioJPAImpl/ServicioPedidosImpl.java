package springboot.tienda.servicioJPAImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springboot.tienda.constants.EstadosPedido;
import springboot.tienda.constants.SQL.ConstantesSQL;
import springboot.tienda.data.webservices.ResumenPedido;
import springboot.tienda.model.Pedido;
import springboot.tienda.model.ProductoCarrito;
import springboot.tienda.model.ProductoPedido;
import springboot.tienda.model.Usuario;
import springboot.tienda.services.ServicioCarrito;
import springboot.tienda.services.ServicioPedidos;


@Service
@Transactional
public class ServicioPedidosImpl implements ServicioPedidos{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	ServicioCarrito servicioCarrito;

	@Override
	public List<Pedido> obtenerPedidos() {
		return entityManager.createQuery("select p from Pedido p order by p.id desc").getResultList();
	}

	@Override
	public Pedido obtenerPedidoPorId(int idPedido) {
		return (Pedido)entityManager.find(Pedido.class, idPedido);
	}

	@Override
	public void actualizarEstadoPedido(int idPedido, String estado) {
		Pedido p = entityManager.find(Pedido.class, idPedido);
		p.setEstado(estado);
		entityManager.merge(p);
		
	}

	@Override
	public void procesarPaso1(String nombre, String apellidos, String direccion, String ciudad, String codigoPostal, String provincia, int idUsuario) {
		//Cada usuario solo podra tener un pedido con estado "en proceso", ese es el envio donde rellenaremos los datos de envio, pago... 
		//Si el usuari finaliza un pedido en estado "en proceso" el estado de dicho pedido pasara a ser "terminado"
		//Puede haber tantos pedidos en estado terminado como se quieraSSSSSSSSS
		Pedido p = obtenerPedidoActual(idUsuario);
		p.setNombre(nombre);
		p.setApellidos(apellidos);
		p.setDireccion(direccion);
		p.setCiudad(ciudad);
		p.setCodigoPostal(codigoPostal);
		p.setProvincia(provincia);
		p.setEstado(EstadosPedido.EN_PROCESO);
		entityManager.merge(p);
		
	}

	@Override
	public void procesarPaso2(String titular, String numero, String tarjeta, String fechaCaducidad, String cvv, int idUsuario) {
		Pedido p = obtenerPedidoActual(idUsuario);
		p.setTitularTarjeta(titular);
		p.setNumeroTarjeta(numero);
		p.setTipoTarjeta(tarjeta);
		p.setFechaCaducidad(fechaCaducidad);
		p.setCvv(cvv);
		entityManager.merge(p);
		
	}

	@Override
	public void procesarPaso3(String personaContacto, String telefonoContacto, int idUsuario) {
		Pedido p = obtenerPedidoActual(idUsuario);
		p.setPersonaContacto(personaContacto);
		p.setTelefonoContacto(telefonoContacto);
		
		System.out.println("[i] -Pedido a procesar: \n" +p.toString());
		entityManager.merge(p);
		
	}

	@Override
	public ResumenPedido obtenerResumenDelPedido(int idUsuario) {
		ResumenPedido resumen = new ResumenPedido();
		Pedido p = obtenerPedidoActual(idUsuario);
		resumen.setNombre(p.getNombre());
		resumen.setApellidos(p.getApellidos());
		resumen.setDireccion(p.getDireccion());
		resumen.setCiudad(p.getCiudad());
		resumen.setCodigoPostal(p.getCodigoPostal());
		resumen.setProvincia(p.getProvincia());
		
		
		resumen.setTitularTarjeta(p.getTitularTarjeta());
		//Hay que tapar numeros de la tarjeta
		resumen.setNumeroTarjeta(p.getNumeroTarjeta());
		resumen.setTipoTarjeta(p.getTipoTarjeta());
		resumen.setFechaCaducidad(p.getFechaCaducidad());
		resumen.setCvv(p.getCvv());
		
		resumen.setPersonaContacto(p.getPersonaContacto());
		resumen.setTelefonoContacto(p.getTelefonoContacto());
		
		resumen.setVideojuegos(servicioCarrito.obtenerProductosCarrito(idUsuario));
		return resumen;
	}

	@Override
	public void confirmarPedido(int idUsuario) {
		Pedido p = obtenerPedidoActual(idUsuario);
		Usuario uBaseDatos = (Usuario)entityManager.find(Usuario.class, idUsuario);
		springboot.tienda.model.Carrito c = uBaseDatos.getCarrito();
		if(c != null && c.getProductosCarrito().size() > 0) {
			for (ProductoCarrito pc : c.getProductosCarrito()) {
				ProductoPedido pp = new ProductoPedido();
				pp.setCantidad(pc.getCantidad());
				pp.setVideojuego(pc.getVideojuego());
				p.getProductosPedidos().add(pp);
				entityManager.persist(pp);
			}
		}
		//Borrar productos del carrito actual
		Query query = 
				entityManager.createNativeQuery(
						ConstantesSQL.SQL_BORRAR_PRODUCTOS_CARRITO);
		query.setParameter("carrito_id", c.getId());
		query.executeUpdate();
		//finalizamos pedido
		p.setEstado(EstadosPedido.TERMINADO);	
		entityManager.merge(p);
	}
	
	
	private Pedido obtenerPedidoActual(int idUsuario) {
		Usuario uBaseDatos = (Usuario)
				entityManager.find(Usuario.class, idUsuario);
		Object pedidoEnProceso = null;
		List<Pedido> resultadoConsulta = entityManager.createQuery(
				"select p from Pedido p where p.estado = :estado and p.usuario.id = :usuario_id ")
				.setParameter("estado", EstadosPedido.EN_PROCESO)
				.setParameter("usuario_id", uBaseDatos.getId()).getResultList();
		if(resultadoConsulta.size() == 1) {
			pedidoEnProceso = resultadoConsulta.get(0);
		}else {
			pedidoEnProceso = null;
		}
		Pedido p = null;
		if (pedidoEnProceso != null ) {
			p = (Pedido)pedidoEnProceso;
		}else {
			p = new Pedido();
			p.setUsuario(uBaseDatos);
		}		
		return p;
	}

}
