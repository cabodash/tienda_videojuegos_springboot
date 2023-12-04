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
	
	@Size(min = 3, max = 40, message = "El nombre debe tener entre 3 y 40 caracteres")
	@NotEmpty(message = "{genero.nombre.notempty}")
	@Pattern(regexp = "[A-Za-z0-9áéíóúÁÉÍÓÚñÑ ' -]+", message = "Solo puede tener letras, números, espacios en blanco, comillas simples y guiones")
	private String nombre;

	@Size(min = 3, max = 200, message = "La descripcion debe tener entre 3 y 200 caracteres")
	@NotEmpty(message = "{plataforma.descripcion.notempty}")
	@Pattern(regexp = "[A-Za-z0-9áéíóúÁÉÍÓÚñÑ ' -]+", message = "Solo puede tener letras, números, espacios en blanco, comillas simples y guiones")
	private String Descripcion;
	
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

	
}