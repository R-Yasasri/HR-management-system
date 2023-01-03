/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rajitha Yasasri
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = "*.jsp")
public class LogInFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String requestURI = req.getRequestURI();
        System.out.println(requestURI);
        HttpSession session = req.getSession();
        if (requestURI.equals("/WebApplication1/Login") || requestURI.equals("/WebApplication1/login.jsp")) {

            if (session.getAttribute("loggedIn") == null) {
                chain.doFilter(request, response);
            } else {
                resp.sendRedirect("addanemployee.jsp");
            }
        } else {

            if (session.getAttribute("loggedIn") == null) {
                resp.sendRedirect("login.jsp");
            } else {
                chain.doFilter(request, response);
            }
        }

    }

}
