/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lk.java.session.AdminBeanRemote;

/**
 *
 * @author Rajitha Yasasri
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @EJB
    private AdminBeanRemote adminBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     
        HttpSession session = req.getSession();
        
        String username = req.getParameter("username");
        String password = req.getParameter("pass");
        
        System.out.println("username"+username);
        
        String pass = adminBean.searchAdminByUserName(username);
        
        System.out.println(pass);
        if (password.equals(pass)) {
            session.setAttribute("loggedIn", true);
            System.out.println("login success");
            resp.sendRedirect("addanemployee.jsp");
        }else{
            
            PrintWriter out = resp.getWriter();
            
            System.out.println("invalid login");
            out.write("Invalid login");
            session.setAttribute("error", "Invalid credentials");
            resp.sendRedirect("login.jsp");
            
        }
    }


    
}
