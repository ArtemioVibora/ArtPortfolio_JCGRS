package com.iacademy.cselec05.userActivities;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
        New Insert Picture Servlet -- Juan Amado Cleto
        Unlike the deprecated InsertServlet formally known as MyServlet

        This only does one thing -- insert pictures -- ah I can hear mad artists -- those anti AI people but anyways
        this is part of my refractoring part holy crap (and I am sorry for the developer who did the insert logic) but
        oof -- this could have been better. But enough of me complaining.

        To be fair for the developer, the logic is already there -- I am going to reuse his logic particularly the inserting
        one and let's see what can be done.
 */
public class InsertPictureServlet extends HttpServlet {

    // get the 'predestined elements and render it through doGet
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/insertPicture.jsp").forward(request,response);
    }

    // Copied the logic from doPost from InsertServlet
    // Few reminders to me and the other developers
    // proper naming conventions ---- we cannot use 'unusualAction' as a variable name. LMAO
    // Because this tells me or other programmers nothing.
    // So we need to have proper naming conventions for development
    // because somebody will maintain our code, or do keep in mind of other developers
    // not going insane. Proper naming conventions = what this thing (variable or function or class) does.
    // I will change the unusualAction into something more meaningful
    // TODO: Investigate what unusualAction does
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String artistName = request.getParameter("artist"); // The artist name
        String artPieceName = request.getParameter("artName"); // The art piece name
        String picture = request.getParameter("unusualAction"); // TODO: Rename unusual Action
    }
}
