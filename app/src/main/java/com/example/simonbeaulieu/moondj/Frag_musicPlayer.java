package com.example.simonbeaulieu.moondj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vegas on 12/08/17.
 */

public class Frag_musicPlayer extends HeritageFragment{
    CentralActivity activity;
    RandomButton randomButton;
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
    }
}
