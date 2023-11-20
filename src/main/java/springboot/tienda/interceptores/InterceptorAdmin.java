package springboot.tienda.interceptores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class InterceptorAdmin implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("Se ejecuta el preHandler: - interceptor");
        System.out.println("Se va a acceder a: " + request.getRequestURI());

        String passAdmin = "";
        if (request.getParameter("pass-login-admin") != null) {
            passAdmin = request.getParameter("pass-login-admin");
            if(passAdmin.equals("123")){
                request.getSession().setAttribute("admin", "ok");
            }
        }
        if (request.getRequestURI().contains("/admin/")) {
            if(!(request.getSession().getAttribute("admin") != null && request.getSession().getAttribute("admin").equals("ok"))){
                response.sendRedirect("../loginAdmin");
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // System.out.println("Se ejecuta el postHandler: - interceptor");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // System.out.println("Se ejecuta el afterCompletion: - interceptor");
    }

}
