package com.example.simonbeaulieu.moondj;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.MenuItem;
import android.os.IBinder;
import android.content.ComponentName;
import android.widget.SeekBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;
import java.util.Collections;

public class CentralActivity extends HeritageActivity {

    public ImageView imageView;
    FrameLayout frameLayout;
    Frag_musicBar frag_musicBar;

    public boolean randomIsActivated;

    //variables pour le service de musique
    private MusicService musicSrv;
    private Intent playIntent;
    private boolean musicBound=false;

    private ServiceConnection musicConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder)service;
            //get the service
            musicSrv=binder.getService();
//            Toast toast = Toast.makeText(HeritageActivity.getCurrentActivityInstance().getApplicationContext(),musicSrv.toString(),Toast.LENGTH_SHORT);
//            toast.show();
            //pass list
            musicSrv.setList(songArrayList);
            musicBound=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            musicBound=false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central);
        setViews();
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

    @Override
    protected void onStart(){
        super.onStart();
        if (playIntent==null){
            System.out.println("onseviceconnected activé");

            playIntent= new Intent(this,MusicService.class);
            bindService(playIntent,musicConnection,Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }
    }

    private void setViews(){
        wrActivity=new WeakReference<HeritageActivity>(this);
        randomIsActivated=false;
        //display du gif bloby
        imageView=(ImageView)findViewById(R.id.gif) ;
        Glide.with(this).load(R.drawable.loading).into(imageView);
        //chien noir
        frameLayout=(FrameLayout)findViewById(R.id.fragmentLayout);
    }




    @Override
    public void onBackPressed() {
        int usedfragmentCount=this.getFragmentManager().getBackStackEntryCount();
        // si on est sur le menu principal
        if (usedfragmentCount==0){
            //si la musique est en cours de lecture
            if(this.getMusicSrv().getIsPlaying()){
                // sort un toast et revient au menu
                Toast toast=new Toast(this);
                toast.setText("in order to leave, please stop the music");

                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);
            }else{
                super.onBackPressed();
            }

        }else{
            this.getFragmentManager().popBackStack();
            frag_musicBar.randomButton.adaptState(randomIsActivated);
        }

    }

    public MusicService getMusicSrv(){
        return musicSrv;
    }
    public Frag_musicBar getMusicbarFragment(){
        return frag_musicBar;
    }
    public Context getContext(){
        return this;
    }


    @Override
    public void onDestroy(){
        stopService(playIntent);
        musicSrv=null;
        super.onDestroy();
    }
}
