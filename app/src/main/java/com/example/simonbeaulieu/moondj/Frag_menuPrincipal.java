package com.example.simonbeaulieu.moondj;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by simon.beaulieu on 07/08/2017.
 */

public class Frag_menuPrincipal extends MainFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mainmenu,
                container, false);
        return view;
    }

}
