package springboot.tienda.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

//@Entity
public class Plataforma {
	
	private String nombre;
	private String descripcion;
	
	//@ManyToMany
	private List<Videojuego> videojueos;
	
	//@Id
	//@GeneratedValue
	private int id;
	
	
	public Plataforma() {
		// TODO Auto-generated constructor stub
	}


	public Plataforma(String nombre, String descripcion, List<Videojuego> videojueos, int id) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.videojueos = videojueos;
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
	
	
	
}
