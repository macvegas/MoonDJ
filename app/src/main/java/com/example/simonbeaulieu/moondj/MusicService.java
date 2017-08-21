package com.example.simonbeaulieu.moondj;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;

import android.media.AudioManager;
import android.os.Binder;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by vegas on 16/08/17.
 */

public class MusicService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {
    //media player
    private MediaPlayer player;
    //song list
    ArrayList<Song> songs;
    //list already played musics
    private int songPosn;
    //binder qui arrive sur le service et provenant de l'activité.
    private final IBinder musicBind = new MusicBinder();
    //position de lecture
    private int musicTimePosition;
    //liste servant de référence aléatoire
    ArrayList<Song> songsRandomed;
    //activité
    CentralActivity centralActivity;
    //song pointée par le player
    Song currentSong;
    //isplaying de l'utilisateur
    boolean isplaying;

    public void onCreate(){
        //creates the service
        super.onCreate();
        //gets activity
        centralActivity=(CentralActivity)HeritageActivity.getCurrentActivityInstance();
        //initialize position
        songPosn=0;
        //creates a player
        player=new MediaPlayer();
        //initialize the mediaplayer the way we want it to be
        initMusicPlayer();
        isplaying=false;

    }

    public void initMusicPlayer(){
        //set player properties: allows the music to keep playing while idle
        player.setWakeMode(getApplicationContext(),
                PowerManager.PARTIAL_WAKE_LOCK);
        //sets the audio type to musicStream
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //adds the listeners needed to the player.
        player.setOnPreparedListener(this);
        player.setOnErrorListener(this);
        player.setOnCompletionListener(this);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicBind;
    }

    @Override
    public boolean onUnbind(Intent intent) {// TODO: 16/08/17 This will execute when the user exits the app, at which point we will stop the service. 
        player.stop();
        player.release();
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
//        songPosn+=1;
//        if(songPosn>=songs.size()){
//            songPosn=0;
//        }
//        setSong(songPosn);
//        playLinearSong();
        toNextSong();
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        //start playback
        mp.start();
        isplaying=true;
        Toast toast = Toast.makeText(HeritageActivity.getCurrentActivityInstance().getApplicationContext(),currentSong.getTitle(),Toast.LENGTH_SHORT);
    }

    //setting method for the music list
    public void setList(ArrayList<Song> songs) {
        this.songs = songs;
        currentSong=this.songs.get(0);
        shuffle();
    }



    //class that will allow the interaction between the activity and the service
    public class MusicBinder extends Binder {
        MusicService getService(){
            return MusicService.this;
        }
    }

    //selector of the song (to be called when user select a song)
    public void setSong(int songIndex){
        songPosn=songIndex;
    }

    //plays the selected song
    public void playLinearSong(){
        player.reset();
        //get the song

        Song playSong = songs.get(songPosn);
        currentSong=playSong;
        try{
            player.setDataSource(playSong.getPath());
        }catch (Exception e){
            Log.e("MUSIC SERVICE","Error setting data source",e);
        }
        player.prepareAsync();
        //ajoute la musique a la liste des musiques jouées
    }

    public void playRandomSong(){
        player.reset();
        //get the song

        Song playSong = songsRandomed.get(songPosn);
        currentSong=playSong;
        try{
            player.setDataSource(playSong.getPath());
        }catch (Exception e){
            Log.e("MUSIC SERVICE","Error setting data source",e);
        }
        player.prepareAsync();
        //ajoute la musique a la liste des musiques jouées
    }

    public void pauseSong(){
        player.pause();
        isplaying=false;
    }

    public void resumeSong(){
        player.start();
        isplaying=true;
    }


    public boolean getIsPlaying(){
        return isplaying;
    }

    public int getRandomRowOf(Song song){
        int row=songsRandomed.size();
        for(int i=0;i<songsRandomed.size();i++){
            if(songsRandomed.get(i)==song){
                row=i;
                break;
            }
        }
        return row;
    }

    //method which creates a random lists
    public void shuffle(){
        songsRandomed=(ArrayList<Song>)songs.clone();
        Collections.shuffle(songsRandomed);
    }

    public void toNextSong(){
        //cas random
        if (centralActivity.randomIsActivated){
            //cas en fin de liste
            if(getRandomRowOf(currentSong)+1>=songsRandomed.size()){
                setSong(0);
            }
            //cas général
            else {
                setSong(getRandomRowOf(currentSong) + 1);
            }
            //reprend la lecture si ca lisait déja
            if(isplaying){
                playRandomSong();
            }
        }
        //cas linéaire
        else{
            //cas en fin de liste
            if(songPosn+1>=songs.size()){
                setSong(0);
            }
            //cas général
            else {
                setSong(songPosn + 1);
            }
            //reprise de lecture si ca lisait deja
            if(isplaying){
                playLinearSong();
            }
        }
    }

    public void toPreviousSong(){
        if (centralActivity.randomIsActivated){
            if(songPosn-1<0){
                setSong(songsRandomed.size()-1);
            }else {
                setSong(songPosn-1);
            }
            if(isplaying){
                playRandomSong();
            }
        }else{
            if(songPosn-1<0){
                setSong(songs.size()-1);
            }else {
                setSong(songPosn-1);
            }
            if(isplaying){
                playLinearSong();
            }
        }
    }
    











    //    public void toNextSong(){
//        boolean wasplaying=getIsPlaying();
//        if(centralActivity.randomIsActivated){
//            for(int i=0;i<songsRandomed.size();i++){
//                if(songsRandomed.get(i)==currentSong){
//
//                }
//            }
//        }
//    }
//    public void toNextSong(){
//        boolean wasplaying = getIsPlaying();
//
//        // si toutes les musiques ont été jouées, liste remise a zero
//        if(songsAlreadyPlayed.size()==songs.size()){
//            songsAlreadyPlayed=new ArrayList<>();
//        }
//        //cas ou la musique était en cours de lecture
//        player.stop();
//        //lecture random activée
//        if(centralActivity.randomIsActivated){
//            //donne un random qui n'a pas déja été joué
//            Random r=new Random();
//            while (songsAlreadyPlayed.contains(songs.get(songPosn))){
//                songPosn= r.nextInt(songs.size());
//            }
//        }
//        // cas ou on est dans une lecture NON random
//        else {
//            songPosn += 1;
//            //verification qu'on n'est pas après la dernière musique. (au dela de la liste)
//            if (songPosn >= songs.size()) {
//                //reprise a 0
//                songPosn = 0;
//            }
//        }
//        //selectionne et lance la musique suivante
//        setSong(songPosn);
//        //si la lecture était activée
//        if (wasplaying){playLinearSong();}
//    }
//
//    public void toPreviousSong(){
//        //remise a zero de la musique jouée si elle a déja été jouée plus de 5 secondes
//        boolean wasplaying = getIsPlaying();
//        if(player.getCurrentPosition()>5000){
//            player.seekTo(0);
//        }else{
//            if(songsAlreadyPlayed.size()>0){
//                songsAlreadyPlayed.remove(songsAlreadyPlayed.size()-1);
//            }
//            player.stop();
//            //vérifie si on a un historique de musiques jouées
//            if(songsAlreadyPlayed.size()>1){
//                Song previousSong = songsAlreadyPlayed.get(songsAlreadyPlayed.size()-2);
//                songPosn=previousSong.getId();
//                setSong(songPosn);
//            }else{
//                //si on a pas d'historique et que le random est activé on récupère une musique random // TODO: 18/08/17 problème: si on revient en arrière et qu'on joue, ca fout dans la liste des musiques jouées
//                if(centralActivity.randomIsActivated){
//                    Random r=new Random();
//                    int random=r.nextInt(songs.size());
//                    while(random==songPosn){
//                        random=r.nextInt(songs.size());
//                    }
//                    songPosn=random;
//                    setSong(songPosn);
//                } else{
//                    if(songPosn>0) {
//                        setSong(songPosn);
//                    }else{
//                        setSong(songs.size()-1);
//                    }
//                }
//            }
//            if(wasplaying){
//                playLinearSong();
//            }
//        }
//    }
}
