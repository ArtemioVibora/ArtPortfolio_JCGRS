package com.iacademy.cselec05.servlet.profile;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
        This is the profile servlet -- from the name itself it only serves the profile.jsp
 */
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; // What is the purpose of this - Juan Amado Cleto

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
    }
}
