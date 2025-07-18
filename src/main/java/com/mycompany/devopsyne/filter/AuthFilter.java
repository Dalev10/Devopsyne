
package com.mycompany.devopsyne.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

// Autor: Diego Alejandro Vergara Ruiz

@WebFilter("/*") // Aplica a todas las rutas
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();

        // Rutas públicas explícitas
        boolean rutaLogin = uri.startsWith(contextPath + "/login") || uri.endsWith("login.jsp");
        boolean rutaRegistro = uri.startsWith(contextPath + "/registro") || uri.endsWith("registro.jsp");
        boolean rutaRecursos = uri.startsWith(contextPath + "/resources");

        // validación de autenticado
        boolean estaLogueado = (session != null && session.getAttribute("usuario") != null);

        if (estaLogueado || rutaLogin || rutaRegistro || rutaRecursos) {
            // Permitir
            chain.doFilter(request, response);
        } else {
            // Redirigir a login
            resp.sendRedirect(contextPath + "/login.jsp");
        }
    }
}
