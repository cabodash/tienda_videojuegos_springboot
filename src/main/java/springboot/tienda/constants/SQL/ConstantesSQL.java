package springboot.tienda.constants.SQL;

public class ConstantesSQL {
	public final static String SQL_OBTENER_VIDEOJUEGOS_PARA_JSON =
			"SELECT v.id, v.nombre, v.descripcion, v.fecha_lanzamiento, v.desarrollador, v.puntuacion, v.precio "
			+ "FROM videojuego as v "
			+ "WHERE v.alta = 1 "
			+ "AND v.nombre like :nombre "
			+ "ORDER BY v.id DESC "
			+ "LIMIT :comienzo, 6";

	public static final String SQL_OBTENER_DETALLES_VIDEOJUEGO = 
		"SELECT v.id, v.nombre, v.descripcion, v.fecha_lanzamiento, v.desarrollador, v.puntuacion, v.precio "
		+ "FROM videojuego as v "
		+ "WHERE v.id = :id ";

	public static final String SQL_OBTENER_GENEROS_PARA_JSON = 
		"SELECT g.nombre "
		+"FROM genero as g, generos_videojuegos as gv "
		+"WHERE g.id = gv.genero_id "
		+"AND gv.videojuego_id = :videojuego_id";

	public static final String SQL_OBTENER_PLATAFORMAS_PARA_JSON = 
		"SELECT p.nombre "
		+"FROM plataforma as p, plataformas_videojuegos as pv "
		+"WHERE p.id = pv.plataforma_id "
		+"AND pv.videojuego_id = :videojuego_id";

	
	public static final String SQL_OBTENER_CATEGORIAS_PARA_DESPLEGABLES = 
			"SELECT id, nombre FROM genero ORDER BY id ASC";


			
	public static final String SQL_OBTENER_PRODUCTOS_CARRITO = 
			"SELECT v.id AS videojuego_id, v.nombre, v.descripcion, v.precio, pc.cantidad "
			+ "FROM videojuego AS v, producto_carrito AS pc "
			+ "WHERE pc.videojuego_id = v.id "
			+ "AND pc.carrito_id = :carrito_id  "
			+ "ORDER BY pc.id ASC";

	public static final String SQL_BORRAR_PRODUCTOS_CARRITO = 
			"DELETE FROM producto_carrito "
			+ "WHERE carrito_id = :carrito_id";
	
	public static final String SQL_BORRAR_PRODUCTO_CARRITO = 
			"DELETE FROM producto_carrito "
			+ "WHERE carrito_id = :carrito_id "
			+ "AND videojuego_id = :videojuego_id";
		
	public static final String SQL_OBTENER_TOTAL_VIDEOJUEGOS = 
			"SELECT COUNT(id) "
			+"FROM videojuego "
			+"WHERE alta = '1' "
			+"AND nombre like :nombre";
	
	public static final String SQL_BORRAR_GENEROS_VIDEOJUEGOSS_POR_ID_VIDEOJUEGO = 
			"DELETE FROM generos_videojuegos "
			+"WHERE videojuego_id = :videojuego_id";
	
	public static final String SQL_BORRAR_PLATAFORMAS_VIDEOJUEGOSS_POR_ID_VIDEOJUEGO = 
			"DELETE FROM plataformas_videojuegos "
			+"WHERE videojuego_id = :videojuego_id";


	public static final String SQL_OBTENER_VIDEOJUEGOS_DATO_JSON = 
			"SELECT DISTINCT v.*" + 
			"FROM videojuego v " + 
			"LEFT JOIN generos_videojuegos gv ON v.id = gv.videojuego_id " +
			"LEFT JOIN genero g ON gv.genero_id = g.id " +
			"LEFT JOIN plataformas_videojuegos pv ON v.id = pv.videojuego_id " +
			"LEFT JOIN plataforma p ON pv.plataforma_id = p.id " + 
			"WHERE v.alta = true " +
			"AND (v.nombre LIKE :dato " + 
			"OR v.desarrollador LIKE :dato " + 
			"OR g.nombre LIKE :dato " + 
			"OR p.nombre LIKE :dato) " + 
			"ORDER BY v.id DESC " +
			"LIMIT :comienzo, 6";

    public static final String SQL_CAMBIAR_CANTIDAD_PRODUCTO_CARRITO = 
			"UPDATE producto_carrito "
			+ "SET cantidad = :cantidad "
			+ "WHERE carrito_id = :carrito_id "
			+ "AND videojuego_id = :videojuego_id";

	public static final String SQL_OBTENER_PEDIDOS_POR_ID_USUARIO = 
			"SELECT p.id, p.nombre, p.apellidos, p.direccion, p.ciudad, p.codigo_postal, p.provincia, " +
			"p.tipo_tarjeta, p.titular_tarjeta, p.numero_tarjeta, p.cvv, p.persona_contacto, p.telefono_contacto, " +
			"SUM(v.precio * pp.cantidad) as precio_total_pedido " +
			"FROM pedido p " +
			"JOIN producto_pedido pp ON p.id = pp.pedido_id " +
			"JOIN videojuego v ON pp.videojuego_id = v.id " +
			"WHERE p.usuario_id = :id_usuario " +
			"GROUP BY p.id";

			//cambiar sql para que traiga datos como precio y eso//
	public static final String SQL_OBTENER_PRODUCTOS_PEDIDO = 
			"SELECT pp.videojuego_id, v.nombre, v.precio, pp.cantidad, (v.precio * pp.cantidad) as precio_total " +
			"FROM producto_pedido pp " +
			"JOIN videojuego v ON pp.videojuego_id = v.id " +
			"WHERE pp.pedido_id = :id_pedido";
   

	
}