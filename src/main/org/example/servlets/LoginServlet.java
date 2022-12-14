package org.example.servlets;

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

        if (email.trim().equalsIgnoreCase(AppConstants.DUMMY_USER_EMAIL) && password.equals(AppConstants.DUMMY_USER_PWD)) {
            RequestDispatcher rd = req.getRequestDispatcher("welcome");
            rd.forward(req, resp);
        } else {
            //include
            resp.setContentType("text/html");
            RequestDispatcher rd = req.getRequestDispatcher("login.html");
            resp.getWriter().println("<b>Bad credentials</b>");
            rd.include(req, resp);
        }
    }
}
