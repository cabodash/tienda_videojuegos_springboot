package springboot.tienda.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Usuario {
	private String nombre;
	private String email;
	private String pass;
	
	@OneToOne
	private Carrito carrito;
	
	@Transient //Con esto decimos a hibernate que no considere este campo
	private MultipartFile fotoSubida;
	
	@Id
    @GeneratedValue
	private int id;
	
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	public Usuario(String nombre, String email, String password) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.pass = password;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Carrito getCarrito() {
		return carrito;
	}
	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	
	
	
	
	
}
