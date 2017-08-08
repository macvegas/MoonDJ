package com.example.simonbeaulieu.moondj;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by simon.beaulieu on 08/08/2017.
 */

public class Frag_musicList extends Fragment{
    ListView listView;
    ArrayList<Song> liste_musiques ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_musiclist,
                container, false);
         listView = (ListView)view.findViewById(R.id.music_list);
        liste_musiques=HeritageActivity.songArrayList;

        //displaying the list of musics
        Adapter_MusicList adapter = new Adapter_MusicList(getActivity().getApplicationContext(),liste_musiques);
        listView.setAdapter(adapter);

        return view;
    }

}
