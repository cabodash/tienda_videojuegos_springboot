package springboot.tienda.servicioJPAImpl;

import java.io.IOException;
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
import springboot.tienda.model.Plataforma;
import springboot.tienda.model.Videojuego;
import springboot.tienda.services.ServicioVideojuegos;

@Service
@Transactional
public class ServicioVideojuegosJPAImpl implements ServicioVideojuegos{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void registrarVideojuego(Videojuego v) {
		Genero g = entityManager.find(Genero.class, v.getIdGenero());
		v.setGenero(g);
		Plataforma p = entityManager.find(Plataforma.class, v.getIdPlataforma());
		v.setPlataformas(p);
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
		return entityManager.createQuery("select v from Videojuego v where v.alta = true order by v.id desc").getResultList();
	}

	@Override
	public void borrarVideojuego(int id) {
		//Ya no borraoms productos sino que los damos de baja
		//entityManager.remove(entityManager.find(Videojuego.class, id));
		Videojuego v = entityManager.find(Videojuego.class, id);
		v.setAlta(false);
		entityManager.merge(v);
		
	}

	@Override
	public Videojuego obtenerVideojuegoPorId(int id) {
		return entityManager.find(Videojuego.class, id);
	}

	@Override
	public void guardarCambiosVideojuego(Videojuego v) {
		Genero g = entityManager.find(Genero.class, v.getIdGenero());
		v.setGenero(g);
		Plataforma p = entityManager.find(Plataforma.class, v.getIdPlataforma());
		v.setPlataformas(p);
		v.setAlta(true);
		if(v.getFotoSubida().getSize() == 0) {
			System.out.println("[i] -No se subio una nueva foto, se mantiene la actual");
			Videojuego vAnterior = entityManager.find(Videojuego.class, v.getId());
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
	public List<Map<String, Object>> obtenerVideojuegosParaFormarJSON(String nombre) {
		Query query = entityManager.createNativeQuery(
				ConstantesSQL.SQL_OBTENER_VIDEOJUEGOS_PARA_JSON);
		NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		nativeQuery.setParameter("nombre","%" + nombre + "%");
		return nativeQuery.getResultList();
	}

	@Override
	public Map<String, Object> obtenerDetallesVideojuego(int idVideojuego) {
		Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_DETALLES_VIDEOJUEGO);
		NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
		nativeQuery.setParameter("id", idVideojuego);
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		return (Map<String, Object>) nativeQuery.getResultList().get(0);
	}

	@Override
	public List<Videojuego> obtenerVideojuegosPorNombre(String nombre) {
		return entityManager.createQuery("select v from Videojuego v where v.alta = true and v.nombre like :nombre order by v.id desc")
		.setParameter("nombre", "%" + nombre + "%")
		.getResultList();
	}
	
	
	
	
	
}
