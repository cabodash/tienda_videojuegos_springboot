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
import springboot.tienda.model.Pedido;
import springboot.tienda.model.ProductoPedido;
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
			Genero mundo_abierto = new Genero("Mundo Abierto", "Descripcion para mundo abierto");
			entityManager.persist(mundo_abierto);
			Genero rpg = new Genero("RPG", "Descripcion para rpg");
			entityManager.persist(rpg);
			Genero shooter = new Genero("Shooter", "Descripcion para shooter");
			entityManager.persist(shooter);
			Genero deportes = new Genero("Deportes", "Descripcion para Deportes");
			entityManager.persist(deportes);
			
			//Preparo unos videojuegos para la tienda
			Videojuego fifa24 = new Videojuego("EA Sports FC 24", "descripcion de EA Sports FC 24", deportes, "PS4", Date.valueOf("2023-09-23"),"EA", 3.3, 60);
			fifa24.setImagenPortada(copiarArchivoBase("http://localhost:8080/recursos_setup/images/videojuegos/fifa24.jpg"));
			fifa24.setVideoPortada(copiarArchivoBase("http://localhost:8080/recursos_setup/videos/videojuegos/fifa24.webm"));
			entityManager.persist(fifa24);
			
			 Videojuego elden_ring = new Videojuego("Elden Ring", "descripcion de Elden Ring", mundo_abierto, "Xbox series X", Date.valueOf("2022-02-25"),"From Software", 6.3, 40);
			elden_ring.setImagenPortada(copiarArchivoBase("http://localhost:8080/recursos_setup/images/videojuegos/elden_ring.jpg"));
			elden_ring.setVideoPortada(copiarArchivoBase("http://localhost:8080/recursos_setup/videos/videojuegos/elden_ring.webm"));
			entityManager.persist(elden_ring);
			
			Videojuego rdr2 = new Videojuego("Red dead redemtion 2", "descripcion de Red dead 2", mundo_abierto, "PS4", Date.valueOf("2018-10-26"),"EA", 9.3, 70);
			rdr2.setImagenPortada(copiarArchivoBase("http://localhost:8080/recursos_setup/images/videojuegos/rdr2.jpg"));
			rdr2.setVideoPortada(copiarArchivoBase("http://localhost:8080/recursos_setup/videos/videojuegos/rdr2.webm"));
			entityManager.persist(rdr2);
			
			Videojuego cyberpunk = new Videojuego("Cyberpunk 2077", "descripcion de Cyberpunk 2077", rpg, "PC", Date.valueOf("2020-12-10"),"CD Projekt Red", 9.5, 55);
			cyberpunk.setImagenPortada(copiarArchivoBase("http://localhost:8080/recursos_setup/images/videojuegos/cyberpunk.jpg"));
			cyberpunk.setVideoPortada(copiarArchivoBase("http://localhost:8080/recursos_setup/videos/videojuegos/cyberpunk.webm"));
			entityManager.persist(cyberpunk);
			

			Videojuego spiderman2 = new Videojuego("Marvel's Spider-Man 2", "descripcion de Marvel's Spider-Man 2", rpg, "PS5", Date.valueOf("2023-10-20"),"Insomniac Games", 9.7, 75.99);
			spiderman2.setImagenPortada(copiarArchivoBase("http://localhost:8080/recursos_setup/images/videojuegos/spiderman2.jpg"));
			spiderman2.setVideoPortada(copiarArchivoBase("http://localhost:8080/recursos_setup/videos/videojuegos/spiderman2.webm"));
			entityManager.persist(spiderman2);
			
			
			
			Usuario u = new Usuario("pepe", "pepe@gmail.com", "4321");
			u.setImagenPerfil(copiarArchivoBase("http://localhost:8080/recursos_setup/images/usuarios/2.jpg"));
			entityManager.persist(u);
			u = new Usuario("Javier", "javier@gmail.com", "1212");
			u.setImagenPerfil(copiarArchivoBase("http://localhost:8080/recursos_setup/images/usuarios/3.jpg"));
			entityManager.persist(u);
			u = new Usuario("Alex", "alex@gmail.com", "1234");
			u.setImagenPerfil(copiarArchivoBase("http://localhost:8080/recursos_setup/images/usuarios/1.jpg"));
			entityManager.persist(u);
			
			
			
			//Pedido preparado para hacer pruebas
			Pedido p = new Pedido();
			p.setNombre("Alex");
			p.setApellidos("Cabo Guisado");
			p.setCiudad("Collado Villalba");
			p.setCodigoPostal("28400");
			p.setDireccion("Calle isla de la toja");
			p.setProvincia("Madrid");
			p.setTitularTarjeta("Alejandro Cabo");
			p.setNumeroTarjeta("1234123412341234");
			p.setTipoTarjeta("VISA");
			p.setFechaCaducidad("12/28");
			p.setCvv("333");
			p.setPersonaContacto("Persona de contacto");
			p.setTelefonoContacto("+34 633 633 633");
			p.setUsuario(u);
			entityManager.persist(p);
			ProductoPedido pp = new ProductoPedido();
			pp.setPedido(p);
			pp.setVideojuego(spiderman2);
			pp.setCantidad(2);
			entityManager.persist(pp);
			
			
			//Pedido preparado para hacer pruebas
			p = new Pedido();
			p.setNombre("Alex 2");
			p.setApellidos("Cabo Guisado 2");
			p.setCiudad("Collado Villalba 2");
			p.setCodigoPostal("28400");
			p.setDireccion("Calle isla de la toja 2");
			p.setProvincia("Madrid 2");
			p.setTitularTarjeta("Alejandro Cabo 2");
			p.setNumeroTarjeta("1234123412341234");
			p.setTipoTarjeta("VISA");
			p.setFechaCaducidad("12/28");
			p.setCvv("333");
			p.setPersonaContacto("Persona de contacto 2");
			p.setTelefonoContacto("+34 633 633 633");
			p.setUsuario(u);
			entityManager.persist(p);
			pp = new ProductoPedido();
			pp.setPedido(p);
			pp.setVideojuego(spiderman2);
			pp.setCantidad(5);
			entityManager.persist(pp);
			
			
			
			SetUp registroSetUp = new SetUp();
			registroSetUp.setCompletado(true);
			entityManager.persist(registroSetUp);
		}
	}
	private byte[] copiarArchivoBase(String ruta_origen) {
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
