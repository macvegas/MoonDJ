package com.example.simonbeaulieu.moondj;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CentralActivity extends HeritageActivity {

    static ImageView chien;
    public ImageView imageView;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central);

        //mise en place du fragment principal
        FragmentTransaction ft=getFT(this);
        Frag_menuPrincipal fragMenuPrincipal = new Frag_menuPrincipal();
        HeritageFragment.instanciate(fragMenuPrincipal,ft,true,false,R.id.fragmentLayout,"MAINMENU");
        setViews();

    }

    public void setViews(){
        //display du gif bloby
        imageView=(ImageView)findViewById(R.id.gif) ;
        Glide.with(this).load(R.drawable.loading).into(imageView);
        //chien noir
        chien=(ImageView)findViewById(R.id.imageView2);
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

    public static void showDog(){

    }

    public Context getContext(){
        return this;
    }
}
