package springboot.tienda.constantesSQL;

public class ConstantesSQL {
	public final static String SQL_OBTENER_VIDEOJUEGOS_PARA_JSON =
			"SELECT v.id, v.nombre, v.descripcion, v.plataformas, v.fecha_lanzamiento, v.desarrollador, v.puntuacion, v.precio, g.nombre as nombre_genero "
			+ "FROM videojuego as v, genero as g "
			+ "WHERE v.genero_id = g.id "
			+ "AND v.alta = 1 "
			+ "ORDER BY v.id DESC";
	
	public static final String SQL_OBTENER_CATEGORIAS_PARA_DESPLEGABLES = 
			"SELECT id, nombre FROM genero ORDER BY id ASC";

	public static final String SQL_OBTENER_PRODUCTOS_CARRITO = 
			"SELECT v.id AS videojuego_id, v.nombre, v.precio, pc.cantidad "
			+ "FROM videojuego AS v, producto_carrito AS pc "
			+ "WHERE pc.videojuego_id = v.id "
			+ "AND pc.carrito_id = :carrito_id  "
			+ "ORDER BY pc.id ASC";

	public static final String SQL_OBTENER_DETALLES_VIDEOJUEGO = 
			"SELECT v.id, v.nombre, v.descripcion, v.plataformas, v.fecha_lanzamiento, v.desarrollador, v.puntuacion, v.precio, g.nombre as nombre_genero "
			+ "FROM videojuego as v, genero as g "
			+ "WHERE v.genero_id = g.id "
			+ "AND v.id = :id ";

	public static final String SQL_BORRAR_PRODUCTOS_CARRITO = 
			"DELETE FROM producto_carrito "
			+ "WHERE carrito_id = :carrito_id";
	
	public static final String SQL_BORRAR_PRODUCTO_CARRITO = 
			"DELETE FROM producto_carrito "
			+ "WHERE carrito_id = :carrito_id "
			+ "AND videojuego_id = :videojuego_id";
	
}
