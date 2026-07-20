package com.iacademy.cselec05.servlet.profile;

import com.iacademy.cselec05.factory.ObjectFactory;
import com.iacademy.cselec05.model.ArtDomain;
import com.iacademy.cselec05.repo.ArtDatabaseRepo;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
            Servlet that renders or seeeeerrrrves the Home servlet responsible for homefeed
 */

public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; // No usage yet, dont know what you are aiming here

    // Get the object factory
    private static final ObjectFactory objectFactory = new ObjectFactory();
    private static final ArtDatabaseRepo dataRepo = objectFactory.getArtRepistory();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // Get the posts
        List<ArtDomain> postList = dataRepo.getPosts();

        // TODO: Find a way to refractor this to a helper class with a helper function
        // This can be refractored to a helper class for more improvement
        // But because it's just a few lines of code we can do without a helper class
        // At this point it's just syntactic sugar -- which I think is overrated
        // Essentially we will see this same for each loop inside the resultArtworkByArtist servlet
        // What this essentially does is convert the bytes to a stringe encoding string if you may
        // and then just sets it for the Art domain objects
        for(ArtDomain pictureForShowing: postList)
        {
            byte [] toConvert = pictureForShowing.getArtPhoto();
            pictureForShowing.setConvertedPicture(Base64.getEncoder().encodeToString(toConvert));
        }

        // TODO: rename insert because its not that meaningful for a variable name but this is only optional
        request.setAttribute("insert", postList);
        request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
    }
}
