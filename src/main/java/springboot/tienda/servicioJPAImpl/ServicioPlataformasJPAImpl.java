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

import springboot.tienda.constants.SQL.ConstantesSQL;
import springboot.tienda.model.Plataforma;
import springboot.tienda.services.ServicioPlataformas;

@Service
@Transactional
public class ServicioPlataformasJPAImpl implements ServicioPlataformas{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Map<String, String> obtenerPlataformasParaDesplegable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Plataforma> obtenerPlataformas() {
		return entityManager.createQuery("select p from Plataforma p").getResultList();
	}

	@Override
    public List<Plataforma> obtenerPlataformasPorIdVideojuego(int idVideojuego) {
        return entityManager.createQuery("SELECT p FROM Plataforma p JOIN p.videojuegos v WHERE v.id = :idVideojuego")
        .setParameter("idVideojuego", idVideojuego)
        .getResultList();
    }

	@Override
	public void registrarPlataforma(Plataforma p) {
		entityManager.persist(p);
		
	}

	@Override
	public void borrarPlataforma(int id) {
		entityManager.remove(entityManager.find(Plataforma.class, id));
		
	}

	@Override
	public Plataforma obtenerPlataformaPorId(int id) {
		return entityManager.find(Plataforma.class, id);
	}

	@Override
	public void guardarCambiosPlataforma(Plataforma p) {
		entityManager.merge(p);
		
	}

	@Override
	public List<Map<String, Object>> obtenerPlataformasParaJSON(int idVideojuego) {
	Query query = entityManager.createNativeQuery( ConstantesSQL.SQL_OBTENER_PLATAFORMAS_PARA_JSON);
	NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
	nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	nativeQuery.setParameter("videojuego_id", idVideojuego);
	return nativeQuery.getResultList();
	}

	@Override
	public List<Plataforma> obtenerPlataformasPorIds(List<Integer> ids) {
		return entityManager.createQuery("SELECT p FROM Plataforma p WHERE p.id IN :ids")
		.setParameter("ids", ids)
		.getResultList();
	}

	@Override
	public void borrarPlataformasVideojuegoPorIdVideojuego(int idVideojuego) {
		Query query = entityManager.createNativeQuery( ConstantesSQL.SQL_BORRAR_PLATAFORMAS_VIDEOJUEGOSS_POR_ID_VIDEOJUEGO);
		NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		nativeQuery.setParameter("videojuego_id", idVideojuego);
		nativeQuery.executeUpdate();	
	}

}
