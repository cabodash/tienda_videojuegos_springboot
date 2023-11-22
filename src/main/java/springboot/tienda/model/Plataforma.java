package springboot.tienda.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;



@Entity
public class Plataforma {
	
	private String nombre;
	private String descripcion;
	
	@OneToMany(mappedBy = "plataformas")
	private List<Videojuego> videojuegos;
	
	@Id
	@GeneratedValue
	private int id;
	
	
	public Plataforma() {}


	public Plataforma(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
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
