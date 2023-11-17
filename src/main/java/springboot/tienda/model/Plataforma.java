package springboot.tienda.model;

import java.util.List;



//@Entity
public class Plataforma {
	
	private String nombre;
	private String descripcion;
	
	//@ManyToMany
	private List<Videojuego> videojuegos;
	
	//@Id
	//@GeneratedValue
	private int id;
	
	
	public Plataforma() {
		// TODO Auto-generated constructor stub
	}


	public Plataforma(String nombre, String descripcion, List<Videojuego> videojuegos, int id) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.videojuegos = videojuegos;
		this.id = id;
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

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public List<Videojuego> getVideojuegos() {
		return videojuegos;
	}


	public void setVideojuegos(List<Videojuego> videojuegos) {
		this.videojuegos = videojuegos;
	}
	
	
	
}
