package springboot.tienda.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Genero {
	private String nombre;
	private String Descripcion;
	
	//Una vez realizada la asiciacion en la clase libro aqui debemos indicar la asociacion inversa
	//Cascade indica como se puede propagar una operacion desde el dato actual
	//CascadeType.ALL indica que una operacion aplicada  a una categoria pueda ser propagada a los libros
	@OneToMany //(cascade = CascadeType.ALL, mappedBy = "categoria", fetch = FetchType.LAZY)
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
