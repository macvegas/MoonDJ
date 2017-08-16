package com.example.simonbeaulieu.moondj;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import android.content.ContentUris;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Binder;
import android.os.PowerManager;
import android.util.Log;

/**
 * Created by vegas on 16/08/17.
 */

public class MusicService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {
    //media player
    private MediaPlayer player;
    //song list
    ArrayList<Song> songs;
    private int songPosn;
    //binder qui arrive sur le service et provenant de l'activité.
    private final IBinder musicBind = new MusicBinder();
    
    public void onCreate(){
        //creates the service
        super.onCreate();
        //initialize position
        songPosn=0;
        //creates a player
        player=new MediaPlayer();
        //initialize the mediaplayer the way we want it to be
        initMusicPlayer();
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

    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        //start playback
        mp.start();
    }

    //selector of the song (to be called when user select a song)
    public void setSong(int songIndex){
        songPosn=songIndex;
    }

    //setting method for the music list
    public void setList(ArrayList<Song> songs) {
        this.songs = songs;
    }

    //class that will allow the interaction between the activity and the service
    public class MusicBinder extends Binder {
        MusicService getService(){
            return MusicService.this;
        }
    }

    public void playSong(){
        //play a song
        player.reset();
        //get the song
        Song playSong = songs.get(songPosn);
        long currSong =playSong.getId();
        Uri trackUri=ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,currSong);
        try{
            player.setDataSource(getApplicationContext(),trackUri);
        }catch (Exception e){
            Log.e("MUSIC SERVICE","Error setting data source",e);
        }
        player.prepareAsync();
    }
}
