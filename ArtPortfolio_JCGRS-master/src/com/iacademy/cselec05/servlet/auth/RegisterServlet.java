package com.iacademy.cselec05.servlet.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.iacademy.cselec05.dao.UserDAO;
import com.iacademy.cselec05.model.User;

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (userDAO.isUsernameExists(username)) {
            request.setAttribute("error", "Username already exists.");
            request.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(request, response);
            return;
        }

        if (userDAO.isEmailExists(email)) {
            request.setAttribute("error", "Email already exists.");
            request.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(request, response);
            return;
        }

        User user = new User(username, email, password);

        if (userDAO.registerUser(user)) {
            response.sendRedirect(request.getContextPath() + "/login?registered=true");
        } else {
            request.setAttribute("error", "Registration failed. Try again.");
            request.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(request, response);
        }
    }
}
