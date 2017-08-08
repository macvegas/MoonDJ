package com.example.simonbeaulieu.moondj;

import android.app.FragmentTransaction;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.util.List;

public class CentralActivity extends HeritageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central);
        FragmentTransaction ft=getFT(this);
        Frag_menuPrincipal fragMenuPrincipal = new Frag_menuPrincipal();
        MainFragment.instanciate(fragMenuPrincipal,ft,true,false,R.id.fragmentLayout);

//        for(Song i: songArrayList){
//            echo(i.getTitle() + " || "+i.getArtist());
//        }
    }
}
