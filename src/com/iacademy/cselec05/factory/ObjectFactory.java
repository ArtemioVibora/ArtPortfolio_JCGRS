package com.iacademy.cselec05.factory;

import com.iacademy.cselec05.repo.ArtDatabaseRepo;

// Comments from Juan Amado Cleto
// This is the database factory for art database -- this can also be used for users
// The reason why we need a repository that is stated
// renamed it to Object Factory -- pascal case -- not camel case because what this class is to return the repository
// It is cleaner that way
// Removed the ArtDatabase newOne because newOne() is confusing to be named for a function
// Go with convention of having getters so we're going to have get "thisRepository".
public class ObjectFactory
{
    // Finally giving this meat -- we need to change repo to a much more meaningful
    // this is because we should have repositories for Users and for Artists
    private static final ArtDatabaseRepo artDatabaseRepo = new ArtDatabaseRepo();

    // Even if it is not static we already have the
    public ArtDatabaseRepo getArtRepistory()
    {
        return artDatabaseRepo;
    }
}
