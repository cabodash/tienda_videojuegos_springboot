package springboot.tienda.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Usuario {


	@Size(min = 3, max = 40, message = "{val.usuario.nombre.size}")
	@NotEmpty(message = "{val.usuario.nombre.notempty}")
	@Pattern(regexp = "[A-Za-z0-9áéíóúÁÉÍÓÚñÑ ' -]+", message = "{val.usuario.nombre.pattern}")
	private String nombre;

	@Size(min = 3, max = 40, message = "{val.usuario.email.size}")
	@NotEmpty(message = "{val.usuario.email.notempty}")
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "{val.usuario.email.pattern}")
	private String email;

	@Size(min = 3, max = 40, message = "{val.usuario.pass.size}")
	@NotEmpty(message = "{val.usuario.pass.notempty}")
	@Pattern(regexp = "[A-Za-z0-9áéíóúÁÉÍÓÚñÑ]+", message = "{val.usuario.pass.pattern}")
	private String pass;

	private boolean alta = true;
	
	@Lob  // Campo tipo clob / blob. Tipo de dato que almacena un array de bytes
    @Column(name = "imagen_perfil")
    private byte[] imagenPerfil;
	
	@OneToOne
	private Carrito carrito;
	
	@Transient //Con esto decimos a hibernate que no considere este campo
	private MultipartFile fotoSubida;
	
	@Id
    @GeneratedValue
	private int id;
	
	
	public Usuario() {}
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
	public byte[] getImagenPerfil() {
		return imagenPerfil;
	}
	public void setImagenPerfil(byte[] imagenPerfil) {
		this.imagenPerfil = imagenPerfil;
	}
	public MultipartFile getFotoSubida() {
		return fotoSubida;
	}
	public void setFotoSubida(MultipartFile fotoSubida) {
		this.fotoSubida = fotoSubida;
	}
	public boolean isAlta() {
		return alta;
	}
	public void setAlta(boolean alta) {
		this.alta = alta;
	}
	

	
	
	
	
	
	
}
