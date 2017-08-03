package com.example.simonbeaulieu.moondj;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class CentralActivity extends HeritageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central);


        DBHandler dbHandler= new DBHandler(this);
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
}
