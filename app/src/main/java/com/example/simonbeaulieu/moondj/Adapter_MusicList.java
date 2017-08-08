package com.example.simonbeaulieu.moondj;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon.beaulieu on 08/08/2017.
 */

public class Adapter_MusicList extends ArrayAdapter {

        //tweets est la liste des models à afficher
        public Adapter_MusicList(Context context, ArrayList<Song> songs) {
            super(context, 0, songs);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list,parent, false);
            }

            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            if(viewHolder == null){
                viewHolder = new ViewHolder();
                viewHolder.titre = (TextView) convertView.findViewById(R.id.Musictitre);
                viewHolder.artiste = (TextView) convertView.findViewById(R.id.Musicartiste);
                viewHolder.notation = (TextView) convertView.findViewById(R.id.Musicnotation);
                convertView.setTag(viewHolder);
            }

            //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
            Song song = (Song) getItem(position);

            //il ne reste plus qu'à remplir notre vue
            viewHolder.titre.setText(song.getTitle());
            viewHolder.artiste.setText(song.getArtist());
            viewHolder.notation.setText(song.getNotation());

            return convertView;
        }

    private class ViewHolder{
        TextView titre;
        TextView artiste;
        TextView notation;

    }
}
