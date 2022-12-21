package org.example.servlets;

import org.example.dao.impl.UsersDAO;
import org.example.model.User;
import org.example.util.AppConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        RequestDispatcher rd = req.getRequestDispatcher("login.html");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        final String password = req.getParameter("pwd");
        resp.setContentType("text/html");
        RequestDispatcher rd = req.getRequestDispatcher("login.html");
        User user = new UsersDAO().getByEmail(email.trim());
        if (user == null) {
            resp.getWriter().println("<b>User does not exist. Please <a href = 'registration'> registration</a></b>");
            rd.include(req, resp);
        } else if (password.equals(user.getPassword())) {
            rd = req.getRequestDispatcher("Welcome");
            rd.forward(req, resp);
        } else {
            resp.getWriter().println("<b>Bad credentials</b>");
            rd.include(req, resp);
        }
    }
}
