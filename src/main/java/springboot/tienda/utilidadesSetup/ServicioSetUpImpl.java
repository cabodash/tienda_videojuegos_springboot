package springboot.tienda.utilidadesSetup;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springboot.tienda.model.Genero;
import springboot.tienda.model.Pedido;
import springboot.tienda.model.Plataforma;
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
			setUp = (SetUp) entityManager.createQuery("select s from SetUp s").getSingleResult();
		} catch (NoResultException e) {
			System.out.println("[i] -No se encontro ningun registro de setup, iniciando setup nuevo");
		}

		if (setUp == null || !setUp.isCompletado()) {
			/*
			 * si no hay nada en la tabla setup o el unico elemento esta en false (no
			 * completado)
			 * se prepara una serie de ragistros para poder testear la tienda
			 */

			// preparo categorias para los videojuegos
			Genero mundo_abierto = new Genero("Mundo Abierto", "Descripcion para mundo abierto");
			entityManager.persist(mundo_abierto);
			Genero rpg = new Genero("RPG", "Descripcion para rpg");
			entityManager.persist(rpg);
			Genero shooter = new Genero("Shooter", "Descripcion para shooter");
			entityManager.persist(shooter);
			Genero deportes = new Genero("Deportes", "Descripcion para Deportes");
			entityManager.persist(deportes);
			Genero multijugador = new Genero("Multijugador", "Descripcion para multijugador");
			entityManager.persist(multijugador);
			Genero plataformas = new Genero("Plataformas", "Descripcion para plataformas");
			entityManager.persist(plataformas);
			Genero puzzle = new Genero("Puzzle", "Descripcion para puzzle");
			entityManager.persist(puzzle);
			Genero terror = new Genero("Terror", "Descripcion para terror");
			entityManager.persist(terror);
			Genero arcade = new Genero("Arcade", "Descripcion para arcade");
			entityManager.persist(arcade);
			Genero estrategia = new Genero("Estrategia", "Descripcion para estrategia");
			entityManager.persist(estrategia);
			Genero porTurnos = new Genero("Por Turnos", "Descripcion para por turnos");
			entityManager.persist(porTurnos);
			Genero conduccion = new Genero("Conduccion", "Descripcion para conduccion");
			entityManager.persist(conduccion);

			// Preparacion de List de generos

			Set<Genero> generosSpiderman2 = new LinkedHashSet<>();
			generosSpiderman2.add(mundo_abierto);
			generosSpiderman2.add(rpg);

			Set<Genero> generosRedDead2 = new LinkedHashSet<>();
			generosRedDead2.add(mundo_abierto);
			generosRedDead2.add(shooter);
			generosRedDead2.add(rpg);

			Set<Genero> generosCyberpunk2077 = new LinkedHashSet<>();
			generosCyberpunk2077.add(mundo_abierto);
			generosCyberpunk2077.add(rpg);

			Set<Genero> generosEASports2024 = new LinkedHashSet<>();
			generosEASports2024.add(deportes);
			generosEASports2024.add(multijugador);

			Set<Genero> generosEldenRing = new LinkedHashSet<>();
			generosEldenRing.add(mundo_abierto);
			generosEldenRing.add(rpg);

			// Preparacion de plataformas
			Plataforma ps3 = new Plataforma("PS3", "PlayStation 3");
			entityManager.persist(ps3);
			Plataforma ps4 = new Plataforma("PS4", "PlayStation 4");
			entityManager.persist(ps4);
			Plataforma ps5 = new Plataforma("PS5", "PlayStation 5");
			entityManager.persist(ps5);
			Plataforma xbox360 = new Plataforma("Xbox 360", "Xbox 360");
			entityManager.persist(xbox360);
			Plataforma xboxOne = new Plataforma("Xbox One", "Xbox One");
			entityManager.persist(xboxOne);
			Plataforma xboxSeriesX = new Plataforma("Xbox Series X", "Xbox Series X");
			entityManager.persist(xboxSeriesX);
			Plataforma nintendoSwitch = new Plataforma("Nintendo Switch", "Nintendo Switch");
			entityManager.persist(nintendoSwitch);
			Plataforma pc = new Plataforma("PC", "PC");
			entityManager.persist(pc);

			List<Plataforma> plataformasFIFA = new ArrayList<>();
			plataformasFIFA.add(ps4);
			plataformasFIFA.add(ps5);
			plataformasFIFA.add(xboxOne);
			plataformasFIFA.add(xboxSeriesX);
			plataformasFIFA.add(pc);

			List<Plataforma> plataformasSpiderman = new ArrayList<Plataforma>();
			plataformasSpiderman.add(ps5);

			for (int i = 0; i < 20; i++) {
				Videojuego videojuego = new Videojuego("Nombre por defecto" + i, "Descripción por defecto"  + i, generosEASports2024, plataformasFIFA, Date.valueOf("2023-10-10"), "Desarrollador por defecto" + i, 5.5, 33.3);
				entityManager.persist(videojuego);
			}

			Videojuego prueba = new Videojuego();
			// Preparo unos videojuegos para la tienda
			Videojuego fifa24 = new Videojuego("EA Sports FC 24", "descripcion de EA Sports FC 24", generosEASports2024, 
			plataformasFIFA, Date.valueOf("2023-09-23"), "EA", 3.3, 60);
			fifa24.setImagenPortada( copiarArchivoBase("http://localhost:8080/recursos_setup/images/videojuegos/fifa24.jpg"));
			fifa24.setVideoPortada( copiarArchivoBase("http://localhost:8080/recursos_setup/videos/videojuegos/fifa24.webm"));
			entityManager.persist(fifa24);
			
			prueba = fifa24;
			
			Videojuego elden_ring = new Videojuego("Elden Ring", "descripcion de Elden Ring", generosEldenRing,
			plataformasFIFA, Date.valueOf("2022-02-25"), "From Software", 6.3, 40);
			elden_ring.setImagenPortada( copiarArchivoBase("http://localhost:8080/recursos_setup/images/videojuegos/elden_ring.jpg"));
			elden_ring.setVideoPortada( copiarArchivoBase("http://localhost:8080/recursos_setup/videos/videojuegos/elden_ring.webm"));
			entityManager.persist(elden_ring);
			
			Videojuego rdr2 = new Videojuego("Red dead redemtion 2", "descripcion de Red dead 2", generosRedDead2,
			plataformasFIFA, Date.valueOf("2018-10-26"), "EA", 9.3, 70);
			rdr2.setImagenPortada( copiarArchivoBase("http://localhost:8080/recursos_setup/images/videojuegos/rdr2.jpg"));
			rdr2.setVideoPortada( copiarArchivoBase("http://localhost:8080/recursos_setup/videos/videojuegos/rdr2.webm"));
			entityManager.persist(rdr2);

			Videojuego cyberpunk = new Videojuego("Cyberpunk 2077", "descripcion de Cyberpunk 2077", generosCyberpunk2077,
			plataformasFIFA, Date.valueOf("2020-12-10"), "CD Projekt Red", 9.5, 55);
			cyberpunk.setImagenPortada( copiarArchivoBase("http://localhost:8080/recursos_setup/images/videojuegos/cyberpunk.jpg"));
			cyberpunk.setVideoPortada( copiarArchivoBase("http://localhost:8080/recursos_setup/videos/videojuegos/cyberpunk.webm"));
			entityManager.persist(cyberpunk);

			Videojuego spiderman2 = new Videojuego("Marvel's Spider-Man 2", "descripcion de Marvel's Spider-Man 2", generosSpiderman2,
			plataformasSpiderman, Date.valueOf("2023-10-20"), "Insomniac Games", 9.7, 75.99);
			spiderman2.setImagenPortada( copiarArchivoBase("http://localhost:8080/recursos_setup/images/videojuegos/spiderman2.jpg"));
			spiderman2.setVideoPortada( copiarArchivoBase("http://localhost:8080/recursos_setup/videos/videojuegos/spiderman2.webm"));
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

			// Pedido preparado para hacer pruebas
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
			pp.setVideojuego(prueba);
			pp.setCantidad(2);
			entityManager.persist(pp);

			// Pedido preparado para hacer pruebas
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
			pp.setVideojuego(prueba);
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
			System.err.println("[-] No se pudo copiar imagen base");
			e.printStackTrace();
		}
		return info;
	}
}
