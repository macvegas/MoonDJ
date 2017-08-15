package com.example.simonbeaulieu.moondj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vegas on 15/08/17.
 */

public class Frag_playlists extends HeritageFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist,
                container, false);
        return view;
        // TODO: 15/08/17 faire le layout des playists
    }
}
