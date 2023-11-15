package springboot.tienda.servicioJPAImpl;



import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springboot.tienda.model.Genero;
import springboot.tienda.model.Usuario;
import springboot.tienda.model.Videojuego;
import springboot.tienda.servicios.ServicioUsuarios;

@Service
@Transactional
public class ServicioUsuariosJPAImpl implements ServicioUsuarios{
	
	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	public List<Usuario> obtenerUsuarios() {
		return entityManager.createQuery("select u from Usuario u").getResultList();
	}

	@Override
	public void registrarUsuario(Usuario u) {
		entityManager.persist(u);
		
	}

	@Override
	public void borrarUsuario(int id) {
		entityManager.remove(entityManager.find(Usuario.class, id));
		
	}

	@Override
	public Usuario obtenerUsuarioPorId(int id) {
		return entityManager.find(Usuario.class, id);
	}

	@Override
	public void guardarCambiosUsuario(Usuario u) {
		if(u.getFotoSubida().getSize() == 0) {
			System.out.println("[i] -No se subio una nueva foto, se mantiene la actual");
			Usuario uAnterior = entityManager.find(Usuario.class, u.getId());
			u.setImagenPerfil(uAnterior.getImagenPerfil());
		}else {
			System.out.println("[i] -Asignar una nueva foto");
			try {
				u.setImagenPerfil(u.getFotoSubida().getBytes());
			} catch (IOException e) {
				System.out.println("[e] -No se pudo procesar la foto subida");
				e.printStackTrace();
			}
		}
		entityManager.merge(u);
		
	}
	
	
	@Override
	public Usuario obtenerUsuarioPorEmailYpass(String email, String pass) {
		Query query = entityManager.createQuery("select u from Usuario u where u.email = :email and u.pass = :pass");
		query.setParameter("email", email);
		query.setParameter("pass", pass);
		
		List<Usuario> resultado = query.getResultList();
		if(resultado.size() == 0) {
			return null;
		}else {
			return resultado.get(0);
		}
	}


	

}
