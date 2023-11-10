package springboot.tienda.utilidadesSetup;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springboot.tienda.model.Genero;
import springboot.tienda.model.Usuario;
import springboot.tienda.model.Videojuego;
import springboot.tienda.model.setup.SetUp;


@Service
@Transactional
public class ServicioSetUpImpl implements ServicioSetUp {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void prepareSetUp() {
		
		SetUp setUp = null;
		
		try {
			setUp = (SetUp)entityManager.createQuery("select s from SetUp s").getSingleResult();
		} catch (NoResultException e) {
			System.out.println("[i] -No se encontro ningun registro de setup, iniciando setup nuevo");
		}
		
		
		
		if (setUp == null || !setUp.isCompletado()) {
			/*si no hay nada en la tabla setup o el unico elemento esta en false (no completado)
			se prepara una serie de ragistros para poder testear la tienda */
			
			//preparo categorias para los videojuegos
			Genero genero = new Genero("Mundo Abierto", "Descripcion para mundo abierto");
			entityManager.persist(genero);
			genero = new Genero("RPG", "Descripcion para rpg");
			entityManager.persist(genero);
			genero = new Genero("Shooter", "Descripcion para shooter");
			entityManager.persist(genero);
			Genero genero2 = new Genero("Deportes", "Descripcion para Deportes");
			entityManager.persist(genero2);
			
			//Preparo unos libros para la tienda
			Videojuego v = new Videojuego("EA Sports FC 24", "descripcion de EA Sports FC 24", genero2, "PS4", Date.valueOf("2023-09-23"),"EA", 3.3, 60);
			v.setImagenPortada(copiarImagenBase("http://localhost:8080/imagenes_base/1.jpg"));
			entityManager.persist(v);
			
			v = new Videojuego("Elden Ring", "descripcion de Elden Ring", genero, "Xbox series X", Date.valueOf("2022-02-25"),"From Software", 6.3, 40);
			v.setImagenPortada(copiarImagenBase("http://localhost:8080/imagenes_base/2.jpg"));
			entityManager.persist(v);
			
			v = new Videojuego("Red dead redemtion 2", "descripcion de Red dead 2", genero, "PS4", Date.valueOf("2018-10-26"),"EA", 9.3, 70);
			v.setImagenPortada(copiarImagenBase("http://localhost:8080/imagenes_base/3.jpg"));
			entityManager.persist(v);
			
			v = new Videojuego("Cyberpunk 2077", "descripcion de Cyberpunk 2077", genero, "PC", Date.valueOf("2020-12-10"),"CD Projekt Red", 9.5, 55);
			v.setImagenPortada(copiarImagenBase("http://localhost:8080/imagenes_base/4.jpg"));
			entityManager.persist(v);
			
			v = new Videojuego("Marvel's Spider-Man 2", "descripcion de Marvel's Spider-Man 3", genero2, "PS5", Date.valueOf("2023-10-20"),"Insomniac Games", 9.7, 75.99);
			v.setImagenPortada(copiarImagenBase("http://localhost:8080/imagenes_base/5.jpg"));
			entityManager.persist(v);
			
			
			
			Usuario u = new Usuario("Alex", "alex@gmail.com", "1234");
			u.setImagenPerfil(copiarImagenBase("http://localhost:8080/imagenes_base_usuario/1.jpg"));
			entityManager.persist(u);
			u = new Usuario("pepe", "pepe@gmail.com", "4321");
			u.setImagenPerfil(copiarImagenBase("http://localhost:8080/imagenes_base_usuario/2.jpg"));
			entityManager.persist(u);
			u = new Usuario("Javier", "javier@gmail.com", "1212");
			u.setImagenPerfil(copiarImagenBase("http://localhost:8080/imagenes_base_usuario/3.jpg"));
			entityManager.persist(u);
			
			
			SetUp registroSetUp = new SetUp();
			registroSetUp.setCompletado(true);
			entityManager.persist(registroSetUp);
		}
	}
	private byte[] copiarImagenBase(String ruta_origen) {
		byte[] info = null;
		try {
			URL url = new URL(ruta_origen);
			info = IOUtils.toByteArray(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("[-] No se pudo copiar imagen base");
			e.printStackTrace();
		}
		return info;
	}
}
