package com.example.simonbeaulieu.moondj;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;

public class CentralActivity extends HeritageActivity {

    public ImageView imageView;
    FrameLayout frameLayout;
    Frag_musicBar frag_musicBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central);
        wrActivity=new WeakReference<HeritageActivity>(this);
        setViews();

        // TODO: 13/08/17 mettre un listener sur le manager de fragment (backstacklistener) pour virer la barre de musique quand c'est le player et la remttre quand ca l'est pas
        //mise en place de la playbar
        FragmentTransaction bartransaction=getFT(this);
        frag_musicBar= new Frag_musicBar();
        HeritageFragment.instanciate(frag_musicBar,bartransaction,true,false,R.id.music_bar,"MUSICBAR");

        //mise en place du fragment principal
        FragmentTransaction ft=getFT(this);
        Frag_menuPrincipal fragMenuPrincipal = new Frag_menuPrincipal();
        HeritageFragment.instanciate(fragMenuPrincipal,ft,true,false,R.id.fragmentLayout,"MAINMENU");




// TODO: 13/08/17 pour afficher les infos musiques dans la playbar il faut les mettres dans le frameLayout de CentralActivity parceque la playbar est utilisée par le MusicPlayer
    }

    public void setViews(){
        //display du gif bloby
        imageView=(ImageView)findViewById(R.id.gif) ;
        Glide.with(this).load(R.drawable.loading).into(imageView);
        //chien noir
        frameLayout=(FrameLayout)findViewById(R.id.fragmentLayout);
    }

    @Override
    public void onBackPressed() {
        int usedfragmentCount=this.getFragmentManager().getBackStackEntryCount();
        if (usedfragmentCount==0){
            super.onBackPressed();
        }else{
            this.getFragmentManager().popBackStack();
        }
    }

    public Frag_musicBar getMusicbarFragment(){
        return frag_musicBar;
    }
    public Context getContext(){
        return this;
    }
}
