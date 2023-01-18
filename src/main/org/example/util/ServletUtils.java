package org.example.util;

import org.example.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

public class ServletUtils {
    public static final String USER = "user";
    public static void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(path);
        rd.forward(request, response);
    }

    public static void saveSessionUser(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();

        session.setAttribute(USER, user);

        System.out.println("User [" + user.getId() + "] in session ID: " + session.getId() + " : " + new Date());
    }

    public static User getSessionUser(HttpServletRequest request) {
        HttpSession session = request.getSession();

        Object userObject = session.getAttribute(USER);
        if (userObject == null) {
            System.out.println("No User in session ID: " + session.getId() + " : " + new Date());
            return null;
        } else {
            return (User) userObject;
        }
    }
}
