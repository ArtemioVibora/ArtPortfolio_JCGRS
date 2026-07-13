package com.iacademy.cselec05.model;

public class artDomain
{
    private byte[] artPhoto;
    private String artistName;
    private String artName;
    private String convertedPicture;

    public byte[] getArtPhoto() {
        return artPhoto;
    }

    public void setArtPhoto(byte[] artPhoto) {
        this.artPhoto = artPhoto;
    }
    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtName() {
        return artName;
    }

    public void setArtName(String artName) {
        this.artName = artName;
    }



    public String getConvertedPicture() {
        return convertedPicture;
    }

    public void setConvertedPicture(String convertedPicture) {
        this.convertedPicture = convertedPicture;
    }
}
