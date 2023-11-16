package springboot.tienda.servicioJPAImpl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springboot.tienda.constantesSQL.ConstantesSQL;
import springboot.tienda.model.Carrito;
import springboot.tienda.model.ProductoCarrito;
import springboot.tienda.model.Usuario;
import springboot.tienda.model.Videojuego;
import springboot.tienda.servicios.ServicioCarrito;

@Service
@Transactional
public class ServicioCarritoImpl implements ServicioCarrito {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void agregarProducto(int idUsuario, int idProducto, int cantidad) {
		Usuario uBaseDatos = (Usuario) entityManager.find(Usuario.class, idUsuario);
		Carrito c = uBaseDatos.getCarrito();
		if (c == null) {
			c = new Carrito();
			c.setUsuario(uBaseDatos);
			uBaseDatos.setCarrito(c);
			entityManager.persist(c);
		}

		boolean producto_en_carrito = false;
		// Ver si el producto ya esta en el carrito y en tal caso incrementes su
		// cantidad
		for (ProductoCarrito pc : c.getProductosCarrito()) {
			if (pc.getVideojuego().getId() == idProducto) {
				producto_en_carrito = true;
				pc.setCantidad(pc.getCantidad() + cantidad);
				entityManager.merge(pc);
			}
		}
		// De no existir el producto en el carrito, se crea un productoCarrito nuevo
		if (!producto_en_carrito) {
			ProductoCarrito pc = new ProductoCarrito();
			pc.setCarrito(c);
			pc.setVideojuego((Videojuego) entityManager.find(Videojuego.class, idProducto));
			pc.setCantidad(cantidad);
			entityManager.persist(pc);
		}
	}

	@Override
	public void actualizarProductoCarrito(int idUsuario, int idProducto, int cantidad) {
		// TODO Auto-generated method stub

	}

	@Override
	public void borrarCarrito(int idUsuario, int idProducto) {
		Usuario u = (Usuario) entityManager.find(Usuario.class, idUsuario);
		Carrito c = u.getCarrito();
		if (c != null) {
			Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_BORRAR_PRODUCTO_CARRITO);
			query.setParameter("carrito_id", c.getId());
			query.setParameter("videojuego_id", idProducto);
			NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
			nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			query.executeUpdate();
		}

	}

	@Override
	public List<Map<String, Object>> obtenerProductosCarrito(int idUsuario) {
		Usuario u = (Usuario) entityManager.find(Usuario.class, idUsuario);
		Carrito c = u.getCarrito();
		List<Map<String, Object>> res = null;
		if (c != null) {
			Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_PRODUCTOS_CARRITO);
			query.setParameter("carrito_id", c.getId());
			NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
			nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			res = nativeQuery.getResultList();
		}
		return res;
	}

}
