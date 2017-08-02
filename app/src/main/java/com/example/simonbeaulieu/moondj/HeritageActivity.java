package com.example.simonbeaulieu.moondj;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

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
}
