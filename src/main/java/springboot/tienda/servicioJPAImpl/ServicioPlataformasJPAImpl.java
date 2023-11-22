package springboot.tienda.servicioJPAImpl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public void registrarPlataforma(Plataforma g) {
		entityManager.persist(g);
		
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
	public void guardarCambiosPlataforma(Plataforma g) {
		entityManager.merge(g);
		
	}

}
