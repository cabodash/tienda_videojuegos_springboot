package springboot.tienda.model.setup;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SetUp {
	
	private boolean completado;
	
	@Id
	@GeneratedValue
	private int id;
	
	public SetUp() {
		// TODO Auto-generated constructor stub
	}

	public SetUp(boolean completado, int id) {
		super();
		this.completado = completado;
		this.id = id;
	}

	public boolean isCompletado() {
		return completado;
	}

	public void setCompletado(boolean completado) {
		this.completado = completado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	

}
