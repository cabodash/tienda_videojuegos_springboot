package springboot.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springboot.tienda.utilidadesSetup.ServicioSetUp;

@Controller
public class InicioController {
	@Autowired
	private ServicioSetUp servicioSetUp;

	@Autowired
	private MessageSource mensajes;

	@RequestMapping()
	public String inicio() {
		servicioSetUp.prepareSetUp();

		//Consulta del idioma de usuario
		String idiomaActual = mensajes.getMessage("idioma", null, LocaleContextHolder.getLocale());
		System.out.println("Idioma actual:" + idiomaActual);
		return "inicio_" + idiomaActual;
	}
	
}
