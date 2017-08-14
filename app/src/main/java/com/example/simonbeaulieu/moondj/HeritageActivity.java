package com.example.simonbeaulieu.moondj;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by simon.beaulieu on 02/08/2017.
 */

public class HeritageActivity extends AppCompatActivity{
    public static boolean isplaying =false;
    public static ArrayList<Song> songArrayList;
    int checkPermission;
    final int WRITE_EXTERNAL_STORAGE_REQUESTINT =100;
    final int READ_EXTERNAL_STORAGE_REQUESTINT =101;
    final int STORAGE_REQUESTINT =102;
    final int PHONE_STATE_REQUESTINT=103;
    public static WeakReference<HeritageActivity> wrActivity= null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHandler dbHandler= new DBHandler(this);
        //checkout des permissions
        reqPermissions();
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        //set up de la liste des musiques
        echo("permissions granted and loading list");
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
//        int count= dbHandler.getSongCount();
//        echo("count = "+count);
//        Song song =dbHandler.getSong("Amazing");
//        echo("id is: "+song.getId());
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
            //parsing du titre/artiste
            String songtitle ="";
            Pattern p = Pattern.compile("^([^-]*) - (.*)");
            Matcher m = p.matcher(i.getName());
            //recherche du cas "ARTISTE - TITRE"
            if(m.find()){
                songtitle=m.group(2);
                song.setArtist(m.group(1));

            }
            else{
                //sinon, on remet tout le titre
                songtitle=(i.getName());
                song.setArtist("unknown");
            }
            //on recherche les trucs entre parenthèses/crochets et on vire
            p=Pattern.compile("([\\[(].*[])])");
            m=p.matcher(songtitle);
            if (m.find()){
                songtitle=(songtitle.replace(m.group(1),""));
            }
            // on vire les seconds tirets inutiles
            p=Pattern.compile("(.*) - .*");
            m=p.matcher(songtitle);
            if(m.find()){
                songtitle=m.group(1);
            }
            songtitle=songtitle.replace(".mp3","");
            song.setTitle(songtitle);
            songlist.add(song);
        }

        Collections.sort(songlist, new Comparator<Song>()
        {
            @Override
            public int compare(Song song, Song t1) {
                return song.getTitle().compareTo(t1.getTitle());
            }
        });
        return songlist;
    }

    public static FragmentTransaction getFT(Activity a){
        android.app.FragmentManager fragmentManager = a.getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        return fragmentTransaction;
    }

    public void reqPermissions(){
        checkPermission= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE );
        if(checkPermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE},120);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 120: {
                echo("ca passe");
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    echo("passé dans le true");

                } else {
                    echo("passé dans le false");
                }
                break;
            }
        }
    }

    public static HeritageActivity getCurrentActivityInstance(){
        return wrActivity.get();
    }

    @Override
    public void onResume(){
        super.onResume();
        wrActivity=new WeakReference<HeritageActivity>(this);
    }

    public static void echo(Object o){
        System.out.println(o);
    }


}
