package springboot.tienda.servicios;

import java.util.List;

import springboot.tienda.model.Usuario;

public interface ServicioUsuarios {
	
	
	//Metodos para la administracion
	List<Usuario> obtenerUsuarios();
	void registrarUsuario(Usuario u);
	void borrarUsuario(int id);
	Usuario obtenerUsuarioPorId(int id);
	void guardarCambiosUsuario(Usuario u);
	
	Usuario obtenerUsuarioPorEmailYpass(String email, String pass);
}
