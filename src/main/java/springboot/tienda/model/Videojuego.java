package springboot.tienda.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;;

@Entity
@Table(name = "videojuego")
public class Videojuego {

	@Size(min = 3, max = 40, message = "{val.videojuego.nombre.size}")
	@NotEmpty(message = "{val.videojuego.nombre.notempty}")
	@Pattern(regexp = "[A-Za-z0-9áéíóúÁÉÍÓÚñÑ ' -]+", message = "{val.videojuego.nombre.pattern}")
	private String nombre;

	@Size(min = 3, max = 200, message = "{val.videojuego.descripcion.size}")
	@NotEmpty(message = "{val.videojuego.descripcion.notempty}")
	@Pattern(regexp = "[A-Za-z0-9áéíóúÁÉÍÓÚñÑ ' -]+", message = "{val.videojuego.descripcion.pattern}")
	private String descripcion;


    private Date fechaLanzamiento;

	@Size(min = 2, max = 40, message = "{val.videojuego.desarrollador.size}")
	@NotEmpty(message = "{val.videojuego.desarrollador.notempty}")
	@Pattern(regexp = "[A-Za-z0-9áéíóúÁÉÍÓÚñÑ ' -]+", message = "{val.videojuego.desarrollador.pattern}")
    private String desarrollador;

	@NotNull(message = "{val.videojuego.puntuacion.notnull}")
	@Min(value = 0L, message = "{val.videojuego.puntuacion.min}")
	@Max(value = 10L, message = "{val.videojuego.puntuacion.max}")
    private double puntuacion;

	@NotNull(message = "{val.videojuego.precio.notnull}")
	@Min(value = 1L, message = "{val.videojuego.precio.min}")
	@Max(value = 1000, message = "{val.videojuego.precio.max}")
    private double precio;
    
    private boolean alta = true;
    
    @Lob  // Campo tipo clob / blob. Tipo de dato que almacena un array de bytes
    @Column(name = "imagen_portada")
    private byte[] imagenPortada;

	@Lob
    @Column(name = "video_portada")
    private byte[] videoPortada;
    
    
    @OneToOne
    private ProductoCarrito productoCarrito;

	//Asociacion entre la clase Videojuego y la clase Categoria
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "generos_videojuegos",
        joinColumns = @JoinColumn(name = "videojuego_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "genero_id", referencedColumnName = "id"))
	private Set<Genero> generos;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "plataformas_videojuegos",
        joinColumns = @JoinColumn(name = "videojuego_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "plataforma_id", referencedColumnName = "id"))
	private List<Plataforma> plataformas;
    
    
    
    @Transient //Con esto decimos a hibernate que no considere este campo
	private MultipartFile fotoSubida;

	@Transient //Con esto decimos a hibernate que no considere este campo
	private MultipartFile videoSubido;
    
    @Id
    @GeneratedValue
    private int id;


    
    
    public Videojuego() {}
    
    public Videojuego(String nombre, String descripcion, Set<Genero> generos, List<Plataforma> plataformas,
			Date fechaLanzamiento, String desarrollador, double puntuacion, double precio) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.generos = generos;
		this.plataformas = plataformas;
		this.fechaLanzamiento = fechaLanzamiento;
		this.desarrollador = desarrollador;
		this.puntuacion = puntuacion;
		this.precio = precio;
		
	}


	public Videojuego(int id, String nombre, String descripcion, Set<Genero> generos, List<Plataforma> plataformas,
			Date fechaLanzamiento, String desarrollador, double puntuacion, double precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.generos = generos;
		this.plataformas = plataformas;
		this.fechaLanzamiento = fechaLanzamiento;
		this.desarrollador = desarrollador;
		this.puntuacion = puntuacion;
		this.precio = precio;
		
	}

	    
    public boolean isAlta() {
		return alta;
	}

	public void setAlta(boolean alta) {
		this.alta = alta;
	}

	

	public byte[] getImagenPortada() {
		return imagenPortada;
	}

	public void setImagenPortada(byte[] imagenPortada) {
		this.imagenPortada = imagenPortada;
	}

	public byte[] getVideoPortada() {
		return videoPortada;
	}


	public void setVideoPortada(byte[] videoPortada) {
		this.videoPortada = videoPortada;
	}

	public String getNombre() {
		return nombre;
	}
	

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Set<Genero> getGeneros() {
		return generos;
	}


	public void setGeneros(Set<Genero> generos) {
		this.generos = generos;
	}


	public List<Plataforma> getPlataformas() {
		return plataformas;
	}


	public void setPlataformas(List<Plataforma> plataformas) {
		this.plataformas = plataformas;
	}


	public Date getFechaLanzamiento() {
		return fechaLanzamiento;
	}


	public void setFechaLanzamiento(Date fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}


	public String getDesarrollador() {
		return desarrollador;
	}


	public void setDesarrollador(String desarrollador) {
		this.desarrollador = desarrollador;
	}


	public double getPuntuacion() {
		return puntuacion;
	}


	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public MultipartFile getFotoSubida() {
		return fotoSubida;
	}


	public void setFotoSubida(MultipartFile fotoSubida) {
		this.fotoSubida = fotoSubida;
	}

	public ProductoCarrito getProductoCarrito() {
		return productoCarrito;
	}

	public void setProductoCarrito(ProductoCarrito productoCarrito) {
		this.productoCarrito = productoCarrito;
	}

	public MultipartFile getVideoSubido() {
		return videoSubido;
	}

	public void setVideoSubido(MultipartFile videoSubido) {
		this.videoSubido = videoSubido;
	}

}