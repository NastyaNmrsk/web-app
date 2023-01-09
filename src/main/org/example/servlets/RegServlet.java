package org.example.servlets;

import org.example.dao.impl.UsersDAO;
import org.example.model.User;
import org.example.util.EncryptDecryptUtils;
import org.example.util.IOUtils;
import org.example.util.MailUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.example.util.EncryptDecryptUtils.encrypt;

@WebServlet("/registration")
public class RegServlet extends HttpServlet {
    private RequestDispatcher rd;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name").trim();
        String email = req.getParameter("email").trim();

        String pwd1 = encrypt(req.getParameter("password1"));
        String pwd2 = encrypt(req.getParameter("password2"));

        RequestDispatcher rd = req.getRequestDispatcher("reg.html");
        resp.setContentType("text/html");
        if (!pwd1.equals(pwd2)) {
            resp.getWriter().println("<b>Passwords are not equal!</b>");
            rd.include(req, resp);
            return;
        }


        UsersDAO dao = new UsersDAO();
        User user = dao.getByEmail(email);
        if (user != null) {
            resp.getWriter().println("<b>Email already exist! please</b> <a href='login'>login<a>");
            rd.include(req, resp);
            return;
        }
//add user
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(pwd1);
        boolean added = dao.insert(newUser);
//send message
        if (added) {
            String content = IOUtils.readFileBuff("C:\\Users\\st\\IdeaProjects\\web-app\\src\\main\\webapp\\templates\\activate.html");
            content = content.replace("{*}", "http://localhost:8080/web-app/activate?email=" + email);
            MailUtils.send(email, "CRAZY USERS APP ACTIVATION", content, null);

            resp.getWriter().println("<b>Thanks for registration. Please check your mailbox...</b>");
            return;
        } else {
            resp.getWriter().println("<b>Some error on server side</b>");
            rd.include(req, resp);
            return;
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("reg.html");
        rd.forward(req, resp);
    }
}
