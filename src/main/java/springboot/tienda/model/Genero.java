package springboot.tienda.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Genero {
	private String nombre;
	private String Descripcion;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "generos_videojuegos",
        joinColumns = @JoinColumn(name = "genero_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "videojuego_id", referencedColumnName = "id"))
	private List<Videojuego> videojuegos = new ArrayList<Videojuego>();
	
	@Id
	@GeneratedValue
	private int id;
	
	public Genero() {
		// TODO Auto-generated constructor stub
	}

	public Genero(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		Descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
