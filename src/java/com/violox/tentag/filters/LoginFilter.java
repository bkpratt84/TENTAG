package com.violox.tentag.filters;

import com.violox.tentag.controllers.LoginController;
import com.violox.tentag.controllers.NavigationController;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter({"/admin/*", "/printer/*", "/property/*", "/login/*"})
public class LoginFilter implements Filter {
    @Inject
    LoginController login;
    
    @Inject
    NavigationController nav;
    
    @Override
    public void destroy() {
        
    }
 
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        //HttpSession session = request.getSession(false);
        //String redirect = request.getContextPath();
        String redirect = "";
        
        if (login.isLoggedIn() == false) {
            login.setLoggedIn(true);
            login.setUsername(request.getUserPrincipal().getName());

            if (request.isUserInRole("Admin")) {
                login.setRole("Admin");
                redirect += nav.toAdmin(false, true);
            } else if (request.isUserInRole("Property")) {
                login.setRole("Property");
                redirect += nav.toProperty(false, true);
            } else if (request.isUserInRole("Printer")) {
                login.setRole("Printer");
                redirect += nav.toPrinter(false, true);
            }
            
            response.sendRedirect(redirect);
        } else {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0); // Proxies.
            
            chain.doFilter(request, response);
        }
    }
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }
}