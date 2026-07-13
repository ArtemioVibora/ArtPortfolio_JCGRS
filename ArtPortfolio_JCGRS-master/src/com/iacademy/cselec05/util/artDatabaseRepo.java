package com.iacademy.cselec05.util;

import com.iacademy.cselec05.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class artDatabaseRepo
{
    private String url = "jdbc:mysql://172.104.165.179:3306/cselec05";
    private String user = "student";
    private String password = "iacademy";

    public void insertArt(artDomain insertArtPiece)
    {
        String insertQuery = "INSERT INTO artist(artPicture,artistName,artName) VALUES(?,?,?)";

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Executes the driver code
            Connection connect = DriverManager.getConnection(url,user,password);
            PreparedStatement prepare = connect.prepareStatement(insertQuery);
            prepare.setBytes(1,insertArtPiece.getArtPhoto());
            prepare.setString(2,insertArtPiece.getArtistName());
            prepare.setString(3,insertArtPiece.getArtName());
            prepare.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList retrievestuff(String artistName)
    {
        ArrayList<artDomain> posts = new ArrayList<>();


        String retrieveQuery = "SELECT * FROM artist WHERE artistName = ?";

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Executes the driver code
            Connection connect = DriverManager.getConnection(url,user,password);
            PreparedStatement insertQuery = connect.prepareStatement(retrieveQuery);
            insertQuery.setString(1,artistName);

            ResultSet result = insertQuery.executeQuery();

            while (result.next())
            {
                artDomain retrievePosts = new artDomain();
                retrievePosts.setArtName(result.getString("artName"));
                retrievePosts.setArtistName(result.getString("artistName"));
                retrievePosts.setArtPhoto(result.getBytes("artPicture"));
                posts.add(retrievePosts);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return posts;

    }


    public ArrayList retrieveallstuff()
    {
        ArrayList<artDomain> posts = new ArrayList<>();


        String retrieveQuery = "SELECT * FROM artist";

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Executes the driver code
            Connection connect = DriverManager.getConnection(url,user,password);
            PreparedStatement insertQuery = connect.prepareStatement(retrieveQuery);


            ResultSet result = insertQuery.executeQuery();

            while (result.next())
            {
                artDomain retrievePosts = new artDomain();
                retrievePosts.setArtName(result.getString("artName"));
                retrievePosts.setArtistName(result.getString("artistName"));
                retrievePosts.setArtPhoto(result.getBytes("artPicture"));
                posts.add(retrievePosts);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return posts;

    }
}
