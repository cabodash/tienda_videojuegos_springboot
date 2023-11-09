package springboot.tienda.servicioJPAImpl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springboot.tienda.model.Genero;
import springboot.tienda.model.Videojuego;
import springboot.tienda.servicios.ServicioGeneros;

@Service
@Transactional
public class ServicioGenerosJPAImpl implements ServicioGeneros{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Map<String, String> obtenerGenerosParaDesplegable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Genero> obtenerGeneros() {
		return entityManager.createQuery("select g from Genero g").getResultList();
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

}
