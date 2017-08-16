package com.example.simonbeaulieu.moondj;

import android.media.Image;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by vegas on 15/08/17.
 */

public class Playlist implements Serializable{
    private String ListName;
    private String musicAmount;
    private ImageView imageDecorative;
    ImageButton optionButton;

    public Playlist(String name){
        this.ListName=name;
    }

    public Playlist(String name, ImageView image){
        this.ListName=name;
        this.imageDecorative=image;
    }

    public Playlist(String name, ImageButton optionButton){ // TODO: 16/08/17 creer une classe pour le bouton pour rendre la chose aisée quant aux actions a effectuer 
        this.ListName=name;
        this.optionButton=optionButton;
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

    public void setImageDecorative(ImageView imageDecorative) {
        this.imageDecorative = imageDecorative;
    }

    public ImageView getImageDecorative() {
        return imageDecorative;
    }

    public void setOptionButton(ImageButton optionButton) {
        this.optionButton = optionButton;
    }

    public ImageButton getOptionButton() {
        return optionButton;
    }
}
