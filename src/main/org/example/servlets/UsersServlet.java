package org.example.servlets;

import org.example.dao.impl.OfficesDAO;
import org.example.dao.impl.UsersDAO;
import org.example.model.User;
import org.example.util.EncryptDecryptUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import static org.example.util.ServletUtils.*;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private UsersDAO dao = new UsersDAO();
    private OfficesDAO officesDao = new OfficesDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Set<User> all = dao.getAll();
        System.out.println("Users size: " + all.size());
        req.setAttribute("users", all);


        String action = req.getParameter("action"); // can be C, U , D or NO SUCH PARAM AT ALL!

        if (action != null)
            switch (action) {
                case "C":
                    req.setAttribute("offices", officesDao.getAll());
                    forward(req, resp, "jsp/add-user.jsp");
                    return;
                case "U":
                    //TODO: Show user form for update (form should be populated)
                    return;
                case "D":
                    //TODO: Show 'Are You Sure Page?'
                    return;
            }

        // open jsp and forward users
        forward(req, resp, "jsp/users.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action.equals("C")) {
            User user = new User();
            user.setName(req.getParameter("name").trim());
            user.setEmail(req.getParameter("email").trim().toLowerCase());
            user.setPassword(EncryptDecryptUtils.encrypt(req.getParameter("password")));

            String officeLocation = req.getParameter("officeLocation"); // BLR, Minks K. Marksa 32
            user.setOffice(officesDao.getByLocation(officeLocation));

            user.setActive(req.getParameter("is_active")!=null);

            boolean isAdded = dao.insert(user);
            req.setAttribute("msg", isAdded ? "User was added" : "User NOT added");
            Set<User> all = dao.getAll();
            System.out.println("Users size: " + all.size());
            req.setAttribute("users", all);
            forward(req, resp, "jsp/users.jsp");

        }

        // open jsp and forward users
        RequestDispatcher rd = req.getRequestDispatcher("jsp/users.jsp");
        rd.forward(req, resp);
    }
}
