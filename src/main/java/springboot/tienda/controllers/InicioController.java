package springboot.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springboot.tienda.utilidadesSetup.ServicioSetUp;

@Controller
public class InicioController {
	@Autowired
	private ServicioSetUp servicioSetUp;

	@RequestMapping()
	public String inicio() {
		servicioSetUp.prepareSetUp();
		return "inicio";
	}
	
}
