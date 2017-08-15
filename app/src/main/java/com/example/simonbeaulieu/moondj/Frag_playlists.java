package com.example.simonbeaulieu.moondj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by vegas on 15/08/17.
 */

public class Frag_playlists extends HeritageFragment {
    ListView listView;
    ArrayList<Playlist> liste_playlists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist,
                container, false);
        listView=(ListView)view.findViewById(R.id.listPlaylists);
        liste_playlists=new ArrayList<Playlist>();
        Playlist createItem=new Playlist("Create a Playlist");
        liste_playlists.add(createItem);
        Adapter_Playlists adapter_playlists=new Adapter_Playlists(getActivity().getApplicationContext(),liste_playlists);
        listView.setAdapter(adapter_playlists);


        return view;
        // TODO: 15/08/17 faire le layout des playists
    }
}
