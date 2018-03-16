package com.example.simonbeaulieu.moondj;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by vegas on 12/08/17.
 */

public class Frag_musicPlayer extends HeritageFragment{
    CentralActivity activity;
    RandomButton randomButton;
    SeekBar progressBar;
    private Runnable moveSeekBarThread;
    MediaPlayer mPlayer;
    Handler handler;
    TextView playingArtist;
    TextView playingMusic;
    TextView playingNotation;

    public void updateMusicInfo(){

        playingArtist.setText(activity.getMusicSrv().currentSong.getArtist());
        playingMusic.setText(activity.getMusicSrv().currentSong.getTitle());
        playingNotation.setText(activity.getMusicSrv().currentSong.getNotation());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_musicplayer,
                container, false);
        linkviews(view);
        randomButton.adaptState(activity.randomIsActivated);
        return view;
    }

    @Override
    public void onDetach() {
        CentralActivity activity = (CentralActivity)HeritageActivity.getCurrentActivityInstance();
        showFragment(activity.getMusicbarFragment(),HeritageActivity.getFT(activity));

        super.onDetach();
    }

    private void linkviews(View view){
        activity=(CentralActivity)HeritageActivity.getCurrentActivityInstance();
        randomButton=view.findViewById(R.id.randomButton);
        mPlayer =activity.getMusicSrv().getMediaPlayer();
        playingMusic=view.findViewById(R.id.playingtitle);
        playingArtist=view.findViewById(R.id.playingartist);
        playingNotation=view.findViewById(R.id.playingnotation);

        updateMusicInfo();
        progressBar=view.findViewById(R.id.playingseekbar);
        progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //todo coder les methodes pour mettre a jour la seekbar
                /*i have solved it. Create a thread that will check the song position every second and move the seekbar based on the song position
                  mediaPos = mplayer.getCurrentPosition();
  mediaMax = mplayer.getDuration();

  seekbar.setMax(mediaMax); // Set the Maximum range of the
  seekbar.setProgress(mediaPos);// set current progress to song's

  handler.removeCallbacks(moveSeekBarThread);
  handler.postDelayed(moveSeekBarThread, 100); //cal the thread after 100 milliseconds


}

  The Move seek bar. Thread to move seekbar based on the current position
  of the song

                private Runnable moveSeekBarThread = new Runnable() {
                    public void run() {
                        if(mplayer.isPlaying()){

                            int mediaPos_new = mPlayer.getCurrentPosition();
                            int mediaMax_new = mPlayer.getDuration();
                            seekbar.setMax(mediaMax_new);
                            seekbar.setProgress(mediaPos_new);

                            handler.postDelayed(this, 100); //Looping the thread after 0.1 second
                        }

                    }
                };
                 */
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        handler=new Handler();
        moveSeekBarThread= new Runnable() {
            public void run() {
                if(activity.getMusicSrv().getIsPlaying()){

                    int mediaPos_new = mPlayer.getCurrentPosition();
                    int mediaMax_new = mPlayer.getDuration();
                    progressBar.setMax(mediaMax_new);
                    progressBar.setProgress(mediaPos_new);

                    handler.postDelayed(this, 1000); //Looping the thread after 1 second
                }

            }
        };



    }
}
