package springboot.tienda.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;




@Entity
public class Plataforma {
	
	@Size(min = 2, max = 40, message = "{val.plataforma.nombre.size}")
	@NotEmpty(message = "{val.plataforma.nombre.notempty}")
	@Pattern(regexp = "[A-Za-z0-9áéíóúÁÉÍÓÚñÑ ' -]+", message = "{val.plataforma.nombre.pattern}")
	private String nombre;

	@Size(min = 3, max = 200, message = "{val.plataforma.descripcion.size}")
	@NotEmpty(message = "{val.plataforma.descripcion.notempty}")
	@Pattern(regexp = "[A-Za-z0-9áéíóúÁÉÍÓÚñÑ ' -]+", message = "{val.plataforma.descripcion.pattern}")
	private String descripcion;

	private boolean alta = true;
	
	


	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "plataformas_videojuegos",
    joinColumns = @JoinColumn(name = "plataforma_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name ="videojuego_id", referencedColumnName = "id"))
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
	public boolean isAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}
	
	
}
