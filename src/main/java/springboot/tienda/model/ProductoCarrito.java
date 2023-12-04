package springboot.tienda.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class ProductoCarrito {
	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private Videojuego videojuego;
	
	@OneToOne
	@JoinColumn(name = "carrito_id")
	private Carrito carrito;
	
	@NotNull(message = "Debes poner una cantidad")
	@Min(value = 1L, message = "La cantidad minima es 1")
	@Max(value = 10L, message = "La cantidad maxima es 10")
	private int cantidad;
	
	@Id
	@GeneratedValue
	private int id;
	

	public Videojuego getVideojuego() {
		return videojuego;
	}

	public void setVideojuego(Videojuego videojuego) {
		this.videojuego = videojuego;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
}
