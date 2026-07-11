package com.iacademy.cselec05.controller;

import com.iacademy.cselec05.domain.artDomain;
import com.iacademy.cselec05.repo.artDatabaseRepo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;

import com.iacademy.cselec05.factory.*;

public class MyServlet extends HttpServlet
{
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        artDatabaseRepo dataRepo = databaseFactory.newOne();
        String jspPage = request.getParameter("page");
        if("insert".equals(jspPage))
        {
            request.setAttribute("inset",dataRepo);
            request.getRequestDispatcher("/WEB-INF/views/insertPicture.jsp").forward(request,response);
        }
        else if ("retrieve".equals(jspPage))
        {
            String artist = request.getParameter("Artistname");
            ArrayList<artDomain> postList = dataRepo.retrievestuff(artist);
            request.setAttribute("postAspects",postList);

            request.setAttribute("inset",dataRepo);
            request.getRequestDispatcher("/WEB-INF/views/search.jsp").forward(request,response);
        }


    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String artistName = request.getParameter("artist"); // The artist name
        String artPieceName = request.getParameter("artName"); // The art piece name
        String picture = request.getParameter("unusualAction"); // The picture of the art piece

        if("picture".equals(picture)) // Determines which page is the request from,usually the name is unusualAction but the value is different depending on the page
        {
            artDomain Domain = new artDomain();
            Part Picture = request.getPart("Picture"); // Gets the inputed picture in its raw version
            InputStream processedPicture = Picture.getInputStream(); // Gets the byte version of the picture

            ByteArrayOutputStream getThePicture = new ByteArrayOutputStream();
            byte [] chosenPicture = new byte[9000];
            int readByte = 0;
            while((readByte = processedPicture.read(chosenPicture,0,chosenPicture.length)) !=-1)
            {
                getThePicture.write(chosenPicture,0,readByte);
            }// This entire sequence from ByteArrayOutputStream to the while loop is convreting said picture bytes into bytes that can fit in a array

            byte [] finalPhoto = getThePicture.toByteArray(); // Puts picture bytes into an array
            Domain.setArtPhoto(finalPhoto);
            Domain.setArtName(artPieceName);
            Domain.setArtistName(artistName);

            artDatabaseRepo dataRepo = databaseFactory.newOne();
            dataRepo.insertArt(Domain);
            request.setAttribute("insert",dataRepo);
            request.getRequestDispatcher("/WEB-INF/views/insertPicture.jsp").forward(request,response);

        }
        else if ("retrieve".equals(picture))
        {
            artDatabaseRepo dataRepo = databaseFactory.newOne();
            String artist = request.getParameter("Artistname");

            ArrayList<artDomain> postList = dataRepo.retrievestuff(artist);

            for(artDomain pictureForShowing: postList)
            {
                byte [] toConvert=pictureForShowing.getArtPhoto();
                pictureForShowing.setConvertedPicture(Base64.getEncoder().encodeToString(toConvert));
            }
            request.setAttribute("postAspects",postList);
            request.getRequestDispatcher("/WEB-INF/views/search.jsp").forward(request,response);
        }
    }
}
