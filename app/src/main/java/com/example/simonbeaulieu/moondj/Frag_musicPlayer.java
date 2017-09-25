package com.example.simonbeaulieu.moondj;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

/**
 * Created by vegas on 12/08/17.
 */

public class Frag_musicPlayer extends HeritageFragment{
    CentralActivity activity;
    RandomButton randomButton;
    SeekBar progressBar;
    private Runnable moveSeekBarThread;
    MediaPlayer mPlayer;
    Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_musicplayer,
                container, false);

        linkviews(view);

        randomButton.adaptState(activity.randomIsActivated);


        return view;
    }

    @Override
    public void onDetach() {
        CentralActivity activity = (CentralActivity)HeritageActivity.getCurrentActivityInstance();
        showFragment(activity.getMusicbarFragment(),HeritageActivity.getFT(activity));

        super.onDetach();
    }

    private void linkviews(View view){
        activity=(CentralActivity)HeritageActivity.getCurrentActivityInstance();
        randomButton=view.findViewById(R.id.randomButton);
        mPlayer =activity.getMusicSrv().getMediaPlayer();

        progressBar=view.findViewById(R.id.playingseekbar);
        progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //todo coder les methodes pour mettre a jour la seekbar

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        handler=new Handler();
        moveSeekBarThread= new Runnable() {
            public void run() {
                if(activity.getMusicSrv().getIsPlaying()){

                    int mediaPos_new = mPlayer.getCurrentPosition();
                    int mediaMax_new = mPlayer.getDuration();
                    progressBar.setMax(mediaMax_new);
                    progressBar.setProgress(mediaPos_new);

                    handler.postDelayed(this, 1000); //Looping the thread after 1 second
                }

            }
        };



    }
}
