package com.example.simonbeaulieu.moondj;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CentralActivity extends HeritageActivity {

    public ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central);

        //mise en place du fragment principal
        FragmentTransaction ft=getFT(this);
        Frag_menuPrincipal fragMenuPrincipal = new Frag_menuPrincipal();
        HeritageFragment.instanciate(fragMenuPrincipal,ft,true,false,R.id.fragmentLayout);
        setViews();

    }

    public void setViews(){
        //display du gif bloby
        imageView=(ImageView)findViewById(R.id.gif) ;
        Glide.with(this).load(R.drawable.loading).into(imageView);
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

    public Context getContext(){
        return this;
    }
}
