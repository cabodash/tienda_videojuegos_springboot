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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Genero {
	
	@Size(min = 2, max = 40, message = "{val.genero.nombre.size}")
	@NotEmpty(message = "{val.genero.nombre.notempty}")
	@Pattern(regexp = "[A-Za-z0-9áéíóúÁÉÍÓÚñÑ ' -]+", message = "{val.genero.nombre.pattern}")
	private String nombre;

	@Size(min = 3, max = 200, message = "{val.genero.descripcion.size}")
	@NotEmpty(message = "{val.genero.descripcion.notempty}")
	@Pattern(regexp = "[A-Za-z0-9áéíóúÁÉÍÓÚñÑ ' -]+", message = "{val.genero.descripcion.pattern}")
	private String Descripcion;

	private boolean alta = true;

	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "generos_videojuegos",
        joinColumns = @JoinColumn(name = "genero_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "videojuego_id", referencedColumnName = "id"))
	private List<Videojuego> videojuegos = new ArrayList<Videojuego>();
	
	@Id
	@GeneratedValue
	private int id;
	
	public Genero() {}

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

	
	public boolean isAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}
	
}