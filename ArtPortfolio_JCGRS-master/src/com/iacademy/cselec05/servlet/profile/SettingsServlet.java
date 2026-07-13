package com.iacademy.cselec05.servlet.profile;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.iacademy.cselec05.dao.UserDAO;
import com.iacademy.cselec05.model.User;
import com.iacademy.cselec05.util.CookieUtil;
import com.iacademy.cselec05.util.SessionUtil;

public class SettingsServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action != null && action.equals("delete")) {
            userDAO.deleteUser(SessionUtil.getUser(request).getUserId());
            SessionUtil.invalidateSession(request);
            CookieUtil.deleteCookies(request, response);
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/pages/settings.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User currentUser = SessionUtil.getUser(request);
        if (currentUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String theme = request.getParameter("theme");

        if (username.trim().isEmpty()) {
            username = currentUser.getUsername();
        }
        if (email.trim().isEmpty()) {
            email = currentUser.getEmail();
        }

        // Validation for uniqueness if changed
        if (!currentUser.getUsername().equals(username) && userDAO.isUsernameExists(username)) {
            request.setAttribute("error", "Username already taken.");
            request.getRequestDispatcher("/WEB-INF/pages/settings.jsp").forward(request, response);
            return;
        }

        if (!currentUser.getEmail().equals(email) && userDAO.isEmailExists(email)) {
            request.setAttribute("error", "Email already taken.");
            request.getRequestDispatcher("/WEB-INF/pages/settings.jsp").forward(request, response);
            return;
        }

        boolean success = userDAO.updateUserSettings(
                currentUser.getUserId(),
                username,
                email,
                password);

        if (success) {
            if (username != null && !username.trim().isEmpty()) {
                currentUser.setUsername(username);
            }
            if (email != null && !email.trim().isEmpty()) {
                currentUser.setEmail(email);
            }
            if (password != null && !password.trim().isEmpty()) {
                // update local hash reference
                currentUser.setPasswordHash(com.iacademy.cselec05.util.PasswordUtil.hashPassword(password));
            }

            // Store theme preference in cookie (valid for 30 days)
            if (theme != null) {
                CookieUtil.addCookie(response, "theme", theme, 60 * 60 * 24 * 30);
            }

            SessionUtil.setSessionAttribute(request, "user", currentUser);
            response.sendRedirect(request.getContextPath() + "/profile?updated=true");
        } else {
            request.setAttribute("error", "Failed to update settings. Try again.");
            request.getRequestDispatcher("/WEB-INF/pages/settings.jsp").forward(request, response);
        }
    }
}
