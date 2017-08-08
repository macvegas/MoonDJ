package com.example.simonbeaulieu.moondj;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CentralActivity extends HeritageActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central);
        FragmentTransaction ft=getFT(this);
        Frag_menuPrincipal fragMenuPrincipal = new Frag_menuPrincipal();
        MainFragment.instanciate(fragMenuPrincipal,ft,true,false,R.id.fragmentLayout);

        imageView=(ImageView)findViewById(R.id.gif) ;
        Glide.with(this).load(R.drawable.loading).into(imageView);
//        for(Song i: songArrayList){
//            echo(i.getTitle() + " || "+i.getArtist());
//        }
    }
}
