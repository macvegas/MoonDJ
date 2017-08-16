package com.example.simonbeaulieu.moondj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by simon.beaulieu on 08/08/2017.
 */

public class Frag_musicList extends HeritageFragment {
    ListView listView;
    ArrayList<Song> liste_musiques ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_musiclist,
                container, false);

        listView = view.findViewById(R.id.music_list);
        liste_musiques=HeritageActivity.songArrayList;

        //displaying the list of musics
        final Adapter_MusicList adapter = new Adapter_MusicList(getActivity().getApplicationContext(),liste_musiques);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Song song = (Song)adapterView.getItemAtPosition(position);
                Toast toast = Toast.makeText(HeritageActivity.getCurrentActivityInstance().getApplicationContext(),song.getTitle(),Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        // TODO: 10/08/2017 arranger un onClick sur les items de la musicList

        return view;
    }

}
