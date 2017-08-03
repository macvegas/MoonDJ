package com.example.simonbeaulieu.moondj;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by simon.beaulieu on 02/08/2017.
 */

public class HeritageActivity extends AppCompatActivity{
    public static boolean isplaying =false;
    public static ArrayList<Song> songArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHandler dbHandler= new DBHandler(this);

        songArrayList=songloading();
        setDBup(dbHandler);
        dbHandler.close();

    }

    public void setDBup(DBHandler dbHandler){

        //checking if the table of musics already exists or not
        SQLiteDatabase db= dbHandler.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        boolean isCreated = false;
        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                if(c.getString(0).equals("musiques")){
                    Log.d("CentralActivity","table déja créée");
                    isCreated=true;
                }
                c.moveToNext();
            }
        }
        c.close();
        if(!isCreated){
            dbHandler.onCreate(dbHandler.getWritableDatabase());
            Log.d("CentralActivity","recréation de la table");
        }
        dbHandler.addSong(new Song("/path/interressant","Amazing","INNA","0.0"));
        dbHandler.addSong(new Song("/path/coucou","Babar","polo","0.0"));
//        dbHandler.deleteSong("Amazing");
        int count= dbHandler.getSongCount();
        echo("count = "+count);
        Song song =dbHandler.getSong("Amazing");
        echo("id is: "+song.getId());
//        dbHandler.dropTableSong();

    }

    public ArrayList<Song> songloading(){
        ArrayList<Song> songlist = new ArrayList<Song>();
        File directory= new File("/storage/3862-6333/musique");
        File[] filelist = directory.listFiles();
        for(File i : filelist){
            Song song = new Song();
            song.setPath(i.getPath());
            song.setNotation("0.0");

            Pattern p = Pattern.compile("(.*) - (.*)\\.mp3");
            Matcher m = p.matcher(i.getName());
            if(m.find()){
                song.setTitle(m.group(2));
                song.setArtist(m.group(1));
            }
            else{
                song.setTitle(i.getName());
                song.setArtist("unknown");
            }
            p=Pattern.compile("([\\[(].*[])])");
            m=p.matcher(song.getTitle());
            if (m.find()){
                song.setTitle(song.getTitle().replace(m.group(1),""));
            }
            songlist.add(song);
        }
        return songlist;
    }


    public static void echo(Object o){
        System.out.println(o);
    }
}
