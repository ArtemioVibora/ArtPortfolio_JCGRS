package com.iacademy.cselec05.factory;

import com.iacademy.cselec05.util.*;

public class databaseFactory
{
    private  static artDatabaseRepo repo;

    public static  artDatabaseRepo newOne()
    {
        if( repo == null)
        {
            repo = new artDatabaseRepo();
        }

        return repo;
    }
}
