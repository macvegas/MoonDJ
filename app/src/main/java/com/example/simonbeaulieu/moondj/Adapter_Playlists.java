package com.example.simonbeaulieu.moondj;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vegas on 15/08/17.
 */

public class Adapter_Playlists extends ArrayAdapter {

    public Adapter_Playlists(Context context, ArrayList<Playlist> playlist) {
        super(context, 0, playlist);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_playlistlist,parent, false);
        }

        Adapter_Playlists.ViewHolder viewHolder = (Adapter_Playlists.ViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new Adapter_Playlists.ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.playlistName);
            viewHolder.deco = (ImageView) convertView.findViewById(R.id.playlistImage);
            viewHolder.optionButton = (ImageButton) convertView.findViewById(R.id.playlist_optionbutton);
            convertView.setTag(viewHolder);
        }// TODO: 16/08/17 probleme de cadrage de la playlist (image trop grosse) 

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Playlist playlist = (Playlist) getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.name.setText(playlist.getListName());
        viewHolder.deco.setImageResource(R.drawable.darkgirl);
        viewHolder.optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 15/08/17 mettre le onclicklistener de la playlist_option_button
            }
        });

        return convertView;
    }

    private class ViewHolder{
        TextView name;
        ImageView deco;
        ImageButton optionButton;
    }
}
