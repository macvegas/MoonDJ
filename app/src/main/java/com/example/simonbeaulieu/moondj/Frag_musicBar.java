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
    ImageButton randomButton;
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
        randomButton=(ImageButton)view.findViewById(R.id.randomButton);
        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(activity.randomIsActivated){
                    activity.randomIsActivated=false;
                    randomButton.setImageResource(R.drawable.random_deactivated);
                }else{
                    activity.randomIsActivated=true;
                    randomButton.setImageResource(R.drawable.random);
                }
            }
        });
    }
}
