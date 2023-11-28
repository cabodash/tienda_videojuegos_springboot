package springboot.tienda.servicioJPAImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springboot.tienda.constants.SQL.ConstantesSQL;
import springboot.tienda.model.Genero;
import springboot.tienda.services.ServicioGeneros;

@Service
@Transactional
public class ServicioGenerosJPAImpl implements ServicioGeneros{
	
	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public List<Genero> obtenerGeneros() {
		return entityManager.createQuery("select g from Genero g", Genero.class).getResultList();
	}

	@Override
	public void registrarGenero(Genero g) {
		entityManager.persist(g);
		
	}

	@Override
	public void borrarGenero(int id) {
		entityManager.remove(entityManager.find(Genero.class, id));
		
	}

	@Override
	public Genero obtenerGeneroPorId(int id) {
		return entityManager.find(Genero.class, id);
	}

	@Override
	public void guardarCambiosGenero(Genero g) {
		entityManager.merge(g);
		
	}

	@Override
	public List<Genero> obtenerGenerosPorIdVideojuego(int idVideojuego) {
		// Implementación del método para obtener los géneros por ID de videojuego
		return entityManager.createQuery("SELECT g FROM Genero g JOIN g.videojuegos v WHERE v.id = :idVideojuego", Genero.class)
			.setParameter("idVideojuego", idVideojuego)
			.getResultList();
	}

	@Override
	public List<Map<String, Object>> obtenerGenerosParaJSON(int idVideojuego) {
		Query query = entityManager.createNativeQuery( ConstantesSQL.SQL_OBTENER_GENEROS_PARA_JSON);
		NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		nativeQuery.setParameter("videojuego_id", idVideojuego);
		return nativeQuery.getResultList();
	}

	@Override
	public List<Genero> obtenerGenerosPorIds(List<Integer> ids) {
			return entityManager.createQuery("SELECT g FROM Genero g WHERE g.id IN :ids", Genero.class)
				.setParameter("ids", ids)
				.getResultList();
	}

	@Override
	public void borrarGenerosVideojuegoPorIdVideojuego(int idVideojuego) {
		Query query = entityManager.createNativeQuery( ConstantesSQL.SQL_BORRAR_GENEROS_VIDEOJUEGOSS_POR_ID_VIDEOJUEGO);
		NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		nativeQuery.setParameter("videojuego_id", idVideojuego);
		nativeQuery.executeUpdate();	
	}

}
