package springboot.tienda.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido {
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
	private List<ProductoPedido> productosPedidos = new ArrayList<ProductoPedido>();
	
	private String estado;
	
	//Se pide en paso 1:
	private String nombre;
	private String apellidos;
	private String direccion;
	private String Ciudad;
	private String codigoPostal;
	private String provincia;
	
	
	//paso 2:
	private String titularTarjeta;
	private String numeroTarjeta;
	private String tipoTarjeta;
	private String fechaCaducidad;
	private String cvv;
	
	//Paso 3
	private String personaContacto;
	private String telefonoContacto;
	
	
	
	
	@ManyToOne(targetEntity = Usuario.class, optional = false)
	private Usuario usuario;
	
	@Id
	@GeneratedValue
	private int id;

	public List<ProductoPedido> getProductosPedidos() {
		return productosPedidos;
	}

	public void setProductosPedidos(List<ProductoPedido> productosPedidos) {
		this.productosPedidos = productosPedidos;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTitularTarjeta() {
		return titularTarjeta;
	}

	public void setTitularTarjeta(String titularTarjeta) {
		this.titularTarjeta = titularTarjeta;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getCiudad() {
		return Ciudad;
	}

	public void setCiudad(String ciudad) {
		Ciudad = ciudad;
	}

	public String getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getPersonaContacto() {
		return personaContacto;
	}

	public void setPersonaContacto(String personaContacto) {
		this.personaContacto = personaContacto;
	}

	public String getTelefonoContacto() {
		return telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	@Override
	public String toString() {
		return "Pedido [productosPedidos=" + productosPedidos + ", estado=" + estado + ", nombre=" + nombre
				+ ", apellidos=" + apellidos + ", direccion=" + direccion + ", Ciudad=" + Ciudad + ", codigoPostal="
				+ codigoPostal + ", provincia=" + provincia + ", titularTarjeta=" + titularTarjeta + ", numeroTarjeta="
				+ numeroTarjeta + ", tipoTarjeta=" + tipoTarjeta + ", fechaCaducidad=" + fechaCaducidad + ", cvv=" + cvv
				+ ", personaContacto=" + personaContacto + ", telefonoContacto=" + telefonoContacto + ", usuario="
				+ usuario + ", id=" + id + "]";
	}
	
	
	
	
	
	
	
	

}
