package com.example.simonbeaulieu.moondj;

import java.io.Serializable;

/**
 * Created by simon.beaulieu on 28/07/2017.
 */
public class Song implements Serializable {

    private String path;
    private String title;
    private String artist;
    private String notation;
//constructeur avec notation
    public Song(String path, String title, String artist,String notation) {
        this.path = path;
        this.title = title;
        this.artist = artist;
        this.notation=notation;
    }
//constructeur sans notation
    public Song(String path, String title, String artist) {
        this.path = path;
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist ;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }

    public String getNotation() {
        return notation;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}

