package com.example.simonbeaulieu.moondj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by vegas on 13/08/17.
 */

public class Frag_musicBar extends HeritageFragment{
    ImageButton playpauseButton;
    ImageButton nextButton;
    ImageButton previousButton;
    RandomButton randomButton;
    CentralActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playbar,
                container, false);

        linkViews(view);

        if (activity.randomIsActivated){
            randomButton.setImageResource(R.drawable.random);
        }
        else{
            randomButton.setImageResource(R.drawable.random_deactivated);
        }
        return view;
    }

    private void linkViews(View view){
        activity=(CentralActivity)HeritageActivity.getCurrentActivityInstance();
        randomButton=view.findViewById(R.id.randomButton);
        playpauseButton=view.findViewById(R.id.playpauseButton);
        nextButton=view.findViewById(R.id.nextButton);
        previousButton=view.findViewById(R.id.previousButton);

        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(activity.randomIsActivated){
                    activity.randomIsActivated=false;
                    randomButton.adaptState(false);
                }else{
                    activity.randomIsActivated=true;
                    randomButton.adaptState(true);
                }
            }
        });

        playpauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activity.getMusicSrv().getIsPlaying()){
                    activity.getMusicSrv().pauseSong();
                    playpauseButton.setImageResource(R.drawable.play1);
                }else{
                    activity.getMusicSrv().resumeSong();
                    playpauseButton.setImageResource(R.drawable.pause1);
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.getMusicSrv().toNextSong();
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.getMusicSrv().toPreviousSong();
            }
        });
    }
}
