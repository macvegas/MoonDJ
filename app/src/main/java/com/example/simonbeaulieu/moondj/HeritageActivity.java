package com.example.simonbeaulieu.moondj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by simon.beaulieu on 02/08/2017.
 */

public class HeritageActivity extends AppCompatActivity{
    public static boolean isplaying =false;
    public static ArrayList<Song> songArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void echo(Object o){
        System.out.println(o);
    }
}
