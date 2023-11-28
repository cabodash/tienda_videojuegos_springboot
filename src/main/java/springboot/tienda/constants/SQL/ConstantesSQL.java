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
			"SELECT v.id AS videojuego_id, v.nombre, v.precio, pc.cantidad "
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


   

	
}