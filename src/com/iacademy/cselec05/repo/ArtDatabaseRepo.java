package com.iacademy.cselec05.repo;

import com.iacademy.cselec05.model.*;
import com.iacademy.cselec05.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/*
* Few changes here -- Juan Amado Cleto
* 1. Centralized the whole thing to one database connection
* 2. Using Tayao's code from the DB connection -- I just simply call getConnection()
* 3. Created an sql dump to create artist with tables art_photo artist_name art_name with auto incremented id
* 4. Comments
* 5. Always have the class name as pascal case -- it goes against convention to have class name into camel case. Hence
* I renamed it to ArtDatabaseRepo -- always remember a class name should always be in pascal case -- not camel case.
* 6. I placed it inside package because it should not be inside util package
* */
public class ArtDatabaseRepo
{
    // TODO: will change this to a local database -- DONE
    /*
    private String url = "jdbc:mysql://172.104.165.179:3306/cselec05";
    private String user = "student";
    private String password = "iacademy";
    */

    // Okay another mistake this should not be inside a function -- so I transferred it
    // Alright -- pet peeves aside the reason why we should have the list outside functions is because
    // we are going to use only one repository sooo basically we need to have this outside because if it is
    // inside a function we must keep in mind of scope -- if we were to place inside a function it is limited locally
    // not globally.
    private final List<ArtDomain> posts = new ArrayList<>();

    // What this function does is insert Art Piece using a parameter insertArtPiece of class ArtDomain
    // changed to uploadArt because this is what we are essentially doing 'uploading' and it is more meaningful
    public void uploadArt(ArtDomain insertArtPiece)
    {
        // good we have this statement
        String insertQuery = "INSERT INTO artist(art_photo,artist_name,art_name) VALUES(?,?,?)";

        // will keep try catch -- it is a good habit to have  -- good job!
        try
        {
            // removed class for name because it is already declared inside the DBConnection
            Connection connect = DBConnection.getConnection(); // Okay this is good that we have a static connection
            PreparedStatement prepare = connect.prepareStatement(insertQuery); // preparedStatement here
            prepare.setBytes(1,insertArtPiece.getArtPhoto()); // we need table column bytes for our local database
            prepare.setString(2,insertArtPiece.getArtistName());
            prepare.setString(3,insertArtPiece.getArtName());
            prepare.executeUpdate();
        }
        catch (Exception e) // thinking of having the SQLException for catch but -- eeeeeh too lazy
        {
            e.printStackTrace();
        }
    }

    // This one should be renamed to retrieve data -- but for a project I will let this slide
    // Anyways, this function retrieves the data from the database into an arraylist named posts
    // and returns it
    // Changed retrievestuff to its proper name
    // I know its long but the intent is clear because we are receiving data from a specific artist
    // Cleared up a warning because this should be List<ArtDomain>
    public List<ArtDomain> retrieveDataFromArtist(String artistName)
    {
        // Alright for this one it we should have the List post here
        // This is because if we were to use post -- the list -- it is redundant
        // no --- it should not be there. Instead we should opt for volatile memory purely because
        // if we think about it we are only listing what the art work is and it should not be remembered by the machine.
        // Therefore the solution for this is to have a volatile List inside this function that is only there to retrieve
        // Once the server refreshes then it can shove off. However there is a danger here if it is volatile then there is
        // this danger of memory leakage -- we don't want but for this project sure
        List<ArtDomain> tempPost = new ArrayList<>();

        String retrieveQuery = "SELECT * FROM artist WHERE artist_name = ?";

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Executes the driver code
            Connection connect = DBConnection.getConnection();
            PreparedStatement insertQuery = connect.prepareStatement(retrieveQuery);
            insertQuery.setString(1,artistName);

            ResultSet result = insertQuery.executeQuery();

            while (result.next())
            {
                ArtDomain retrievePosts = new ArtDomain();
                retrievePosts.setArtName(result.getString("art_name"));
                retrievePosts.setArtistName(result.getString("artist_name"));
                retrievePosts.setArtPhoto(result.getBytes("art_picture"));
                tempPost.add(retrievePosts);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // We are returning tempPost -- if the user were to search a post from an artist have another List inside the servlet
        // to serve the posts -- ah yes I love Postal hahaha
        return tempPost;

    }

    // Okay this is the same with retrieveStuff but this one lists all the posts from the arraylist
    // change the name to show all art data
    // Okay named it to retrieveAllData because it sounds more fitting
    // So what this function is that retrieves all data from the table artist -- okay sounds good
    public List<ArtDomain> getPosts()
    {
        String retrieveQuery = "SELECT * FROM artist";

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Executes the driver code
            Connection connect = DBConnection.getConnection();
            PreparedStatement insertQuery = connect.prepareStatement(retrieveQuery);


            ResultSet result = insertQuery.executeQuery();

            while (result.next())
            {
                ArtDomain retrievePosts = new ArtDomain();
                retrievePosts.setArtName(result.getString("art_name"));
                retrievePosts.setArtistName(result.getString("artist_name"));
                retrievePosts.setArtPhoto(result.getBytes("art_picture"));
                posts.add(retrievePosts);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return this.posts;
    }
}
