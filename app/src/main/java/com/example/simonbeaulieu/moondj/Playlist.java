package com.example.simonbeaulieu.moondj;

import android.widget.Button;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by vegas on 15/08/17.
 */

public class Playlist implements Serializable{
    private String ListName;
    private String musicAmount;
    private ImageView imageDecorative;
    Button optionButton;

    public Playlist(String name){
        this.ListName=name;
    }

    public String getListName() {
        return ListName;
    }

    public void setListName(String listName) {
        ListName = listName;
    }

    public String getMusicAmount() {
        return musicAmount;
    }

    public void setMusicAmount(String musicAmount) {// TODO: 15/08/17 faire attention au type de setMusicAmount -> a voir
        this.musicAmount = musicAmount;
    }
}
