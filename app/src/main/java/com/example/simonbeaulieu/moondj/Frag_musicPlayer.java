package com.example.simonbeaulieu.moondj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vegas on 12/08/17.
 */

public class Frag_musicPlayer extends HeritageFragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_musicplayer,
                container, false);
        return view;
    }

    @Override
    public void onPause() {
        CentralActivity activity = (CentralActivity)getActivity();
        showFragment(activity.getMusicbarFragment(),HeritageActivity.getFT(activity));
        super.onPause();
    }
}
