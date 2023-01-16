package org.example.servlets;

import org.example.dao.impl.UsersDAO;
import org.example.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Set;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    private UsersDAO dao = new UsersDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<User> all = dao.getAll();
        System.out.println("Users size: " + all.size());
        req.setAttribute("users", all);
        for (Field f : User.class.getDeclaredFields()) {
            f.getName();
        }
        //open jsp and forward users

        RequestDispatcher rd = req.getRequestDispatcher("jsp/users.jsp");
        rd.forward(req, resp);
    }
}
