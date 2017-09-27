package com.example.simonbeaulieu.moondj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by simon.beaulieu on 07/08/2017.
 */

public class Frag_menuPrincipal extends HeritageFragment {
    CentralActivity activity;
    Button defaultMode;
    Button randomMode;
    Button music;
    Button weightedMode;
    Button playlists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mainmenu,
                container, false);
        setUi(view);
        return view;
    }

    public void setUi(View view){
        activity=(CentralActivity)HeritageActivity.getCurrentActivityInstance();
        defaultMode=(Button)view.findViewById(R.id.deefaultmodebutton);
        randomMode=(Button)view.findViewById(R.id.randommodebutton);
        music=(Button)view.findViewById(R.id.musicstashbutton);
        weightedMode=(Button)view.findViewById(R.id.weightedmodebutton);
        playlists=(Button)view.findViewById(R.id.playlistbutton);

        //listeners
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Frag_musicList musicList =new Frag_musicList();
                instanciate(musicList,CentralActivity.getFT(getActivity()),true,true,R.id.fragmentLayout,"musicListDisplayed");
            }
        });

        randomMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Frag_musicPlayer fragMusicPlayer=activity.fragMusicPlayer;   //new Frag_musicPlayer();
                instanciate(fragMusicPlayer,CentralActivity.getFT(getActivity()),true,true,R.id.fragmentLayout,"playerDisplayed");
                CentralActivity activity =(CentralActivity) getActivity();
                hideFragment(activity.getMusicbarFragment(),HeritageActivity.getFT(activity));
                // TODO: 15/08/17 rajouter le swapping de "random" si il est pas activé déja
            }
        });

        defaultMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Frag_musicPlayer fragMusicPlayer=activity.fragMusicPlayer;
                instanciate(fragMusicPlayer,CentralActivity.getFT(getActivity()),true,true,R.id.fragmentLayout,"playerDisplayed");
                CentralActivity activity =(CentralActivity) getActivity();
                hideFragment(activity.getMusicbarFragment(),HeritageActivity.getFT(activity));
            }
        });

        weightedMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 10/08/2017 implementer onclick weightedmode button
            }
        });

        playlists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Frag_playlists fragPlaylist= new Frag_playlists();
                instanciate(fragPlaylist,CentralActivity.getFT(getActivity()),true,true,R.id.fragmentLayout,"playlistsDisplayed");
            }
        });
    }

}
