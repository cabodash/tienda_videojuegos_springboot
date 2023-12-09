package springboot.tienda.servicioJPAImpl;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springboot.tienda.constants.SQL.ConstantesSQL;
import springboot.tienda.model.Videojuego;
import springboot.tienda.services.ServicioGeneros;
import springboot.tienda.services.ServicioPlataformas;
import springboot.tienda.services.ServicioVideojuegos;

@Service
@Transactional
public class ServicioVideojuegosJPAImpl implements ServicioVideojuegos{

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ServicioPlataformas servicioPlataformas;

	@Autowired
	private ServicioGeneros servicioGeneros;

	@Override
	public void registrarVideojuego(Videojuego v) {
		try {
		v.setImagenPortada(v.getFotoSubida().getBytes());
		entityManager.persist(v);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Videojuego> obtenerVideojuegos() {
		// JPQL -> lenguaje de consultas de jpa, es muy similar a sql
		// ventaja: devuelve objetos
		return entityManager.createQuery("select v from Videojuego v order by v.id desc").getResultList();
	}

	

	@Override
	public Videojuego obtenerVideojuegoPorId(int id) {
		return entityManager.find(Videojuego.class, id);
	}

	@Override
	public void guardarCambiosVideojuego(Videojuego v, List<Integer> generosSeleccionados, List<Integer> plataformasSeleccionadas) {
		v.setAlta(true);
		//Primero hay que borrar las relaciones entre el videojuego y las entidades para evitar un error de multiple merges in videojuego
		servicioGeneros.borrarGenerosVideojuegoPorIdVideojuego(v.getId());
		servicioPlataformas.borrarPlataformasVideojuegoPorIdVideojuego(v.getId());
		v.setGeneros(new HashSet<>(servicioGeneros.obtenerGenerosPorIds(generosSeleccionados)));
		v.setPlataformas(servicioPlataformas.obtenerPlataformasPorIds(plataformasSeleccionadas));
		if(v.getFotoSubida().getSize() == 0) {
			Videojuego vAnterior = entityManager.find(Videojuego.class, v.getId());
			System.out.println("[i] -No se subio una nueva foto, se mantiene la actual: " + vAnterior.getImagenPortada());
			v.setImagenPortada(vAnterior.getImagenPortada());
		}else {
			System.out.println("[i] -Asignar una nueva foto");
			try {
				v.setImagenPortada(v.getFotoSubida().getBytes());
			} catch (IOException e) {
				System.out.println("[e] -No se pudo procesar la foto subida");
				e.printStackTrace();
			}
		}
		entityManager.merge(v);
		 
	}

	

	@Override
	public List<Videojuego> obtenerVideojuegosPorNombre(String nombre) {
		return entityManager.createQuery("select v from Videojuego v where v.nombre like :nombre order by v.id desc")
		.setParameter("nombre", "%" + nombre + "%")
		.getResultList();
	}

	@Override
	public List<Videojuego> obtenerVideojuegosPorNombreComienzoFin(String nombre, int comienzo,
			int resultadosPorPagina) {
		return entityManager.createQuery("select v from Videojuego v where v.nombre like :nombre order by v.id desc")
		.setParameter("nombre", "%" + nombre + "%")
		.setFirstResult(comienzo)
		.setMaxResults(resultadosPorPagina)
		.getResultList();
	}

	@Override
	public int obtenerTotalVideojuegos (String nombre) {
		Query q = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_TOTAL_VIDEOJUEGOS)
		.setParameter("nombre",  "%" + nombre + "%");
		int totalVideojuegos = Integer.parseInt((q.getSingleResult().toString()));
		return totalVideojuegos;
	}
	
	
	//Metodos para cliente
	@Override
	public List<Map<String, Object>> obtenerVideojuegosParaFormarJSON(String dato, int comienzo) {
		Query query = entityManager.createNativeQuery(
				ConstantesSQL.SQL_OBTENER_VIDEOJUEGOS_DATO_JSON);
		NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		nativeQuery.setParameter("dato","%" + dato + "%");
		nativeQuery.setParameter("comienzo", comienzo);
		List<Map<String, Object>> videojuegos = nativeQuery.getResultList();
		return videojuegos;
	}

	@Override
	public Map<String, Object> obtenerDetallesVideojuego(int idVideojuego) {
		Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_DETALLES_VIDEOJUEGO);
		NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
		nativeQuery.setParameter("id", idVideojuego);
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		Map<String, Object> detallesVideojuego = (Map<String, Object>) nativeQuery.getResultList().get(0);
		List<Map<String, Object>> plataformas = servicioPlataformas.obtenerPlataformasParaJSON(idVideojuego);
		detallesVideojuego.put("plataformas", plataformas);
		List<Map<String, Object>> generos = servicioGeneros.obtenerGenerosParaJSON(idVideojuego);
		detallesVideojuego.put("generos", generos);
		return detallesVideojuego;
	}

	@Override
	public List<Videojuego> obtenerVideojuegosDatoPaginado(String dato, int comienzo, int resultadosPorPagina) {
		String qlString = 
			"select distinct v from Videojuego v "
			+ "left join v.generos g "
			+ "left join v.plataformas p "
			+ "where (v.nombre like :dato "
			+ "or v.desarrollador like :dato "
			+ "or g.nombre like :dato "
			+ "or p.nombre like :dato) "
			+ "order by v.id desc";
		return entityManager.createQuery(qlString)
		.setParameter("dato", "%" + dato + "%")
		.setFirstResult(comienzo)
		.setMaxResults(resultadosPorPagina)
		.getResultList();
	}

	@Override
	public void bajaVideojuego(int id) {
		//Ya no borramos productos sino que los damos de baja
		//entityManager.remove(entityManager.find(Videojuego.class, id));
		Videojuego v = entityManager.find(Videojuego.class, id);
		v.setAlta(false);
		entityManager.merge(v);
		
	}

	@Override
	public void altaVideojuego(int id) {
		Videojuego v = entityManager.find(Videojuego.class, id);
		v.setAlta(true);
		entityManager.merge(v);
	}	

	
}