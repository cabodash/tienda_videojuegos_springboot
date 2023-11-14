package springboot.tienda.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;;

@Entity
@Table(name = "videojuego")
public class Videojuego {
	private String nombre;
	private String descripcion;
    private String plataformas;
    private Date fechaLanzamiento;
    private String desarrollador;
    private double puntuacion;
    private double precio;
    
    @Lob  // Campo tipo clob / blob. Tipo de dato que almacena un array de bytes
    @Column(name = "imagen_portada")
    private byte[] imagenPortada;
    
    
    @OneToOne
    private ProductoCarrito productoCarrito;
    
    //Asociacion entre la clase Videojuego y la clase Categoria
    @ManyToOne //(cascade = CascadeType.MERGE, targetEntity = Genero.class,optional = false, fetch = FetchType.LAZY)
    private Genero genero;
    
    @Transient
    private int idGenero;
    
    
    @Transient //Con esto decimos a hibernate que no considere este campo
	private MultipartFile fotoSubida;
    
    @Id
    @GeneratedValue
    private int id;
    
    
    public Videojuego() {
		// TODO Auto-generated constructor stub
	}
    
    public Videojuego(String nombre, String descripcion, Genero genero, String plataformas,
			Date fechaLanzamiento, String desarrollador, double puntuacion, double precio) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.genero = genero;
		this.plataformas = plataformas;
		this.fechaLanzamiento = fechaLanzamiento;
		this.desarrollador = desarrollador;
		this.puntuacion = puntuacion;
		this.precio = precio;
		
	}


	public Videojuego(int id, String nombre, String descripcion, Genero genero, String plataformas,
			Date fechaLanzamiento, String desarrollador, double puntuacion, double precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.genero = genero;
		this.plataformas = plataformas;
		this.fechaLanzamiento = fechaLanzamiento;
		this.desarrollador = desarrollador;
		this.puntuacion = puntuacion;
		this.precio = precio;
		
	}

	public byte[] getImagenPortada() {
		return imagenPortada;
	}

	public void setImagenPortada(byte[] imagenPortada) {
		this.imagenPortada = imagenPortada;
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


	public Genero getGenero() {
		return genero;
	}


	public void setGenero(Genero genero) {
		this.genero = genero;
	}


	public String getPlataformas() {
		return plataformas;
	}


	public void setPlataformas(String plataformas) {
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

	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}
    
	
    
	

}