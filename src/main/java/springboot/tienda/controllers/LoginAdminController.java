package springboot.tienda.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginAdminController {

    @Autowired
    private MessageSource mensajes;
    
    @RequestMapping("loginAdmin")
    public String loginAdmin(){

        return "admin/loginAdmin";
    }

    @RequestMapping("logoutAdmin")
    public String logoutAdmin(HttpServletRequest request){
        request.getSession().invalidate();
        //Consulta del idioma de usuario
		String idiomaActual = mensajes.getMessage("idioma", null, LocaleContextHolder.getLocale());
		System.out.println("Idioma actual:" + idiomaActual);
		return "redirect:/inicio_" + idiomaActual;
    }
}
