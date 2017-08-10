package com.example.simonbeaulieu.moondj;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by simon.beaulieu on 07/08/2017.
 */

public class Frag_menuPrincipal extends MainFragment {
    Button defaultMode;
    Button randomMode;
    Button music;
    Button weightedMode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mainmenu,
                container, false);
        setUi(view);
        return view;
    }

    public void setUi(View view){
        defaultMode=(Button)view.findViewById(R.id.deefaultmodebutton);
        randomMode=(Button)view.findViewById(R.id.randommodebutton);
        music=(Button)view.findViewById(R.id.musicstashbutton);
        weightedMode=(Button)view.findViewById(R.id.weightedmodebutton);

        //listeners
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Frag_musicList musicList =new Frag_musicList();
                getActivity().getFragmentManager().beginTransaction().replace(R.id.fragmentLayout,musicList).commit();
                // TODO: 10/08/2017 mettre le petit gif en haut a coté du titre et faire disparaitre le chien eventuellement
            }
        });

        randomMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 10/08/2017 implementer onclick randomMode button
            }
        });

        defaultMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 10/08/2017 implementer onclick defaultmode button
            }
        });

        weightedMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 10/08/2017 implementer onclick weightedmode button
            }
        });
    }

}
