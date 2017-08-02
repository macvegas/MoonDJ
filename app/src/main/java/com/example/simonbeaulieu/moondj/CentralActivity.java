package com.example.simonbeaulieu.moondj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CentralActivity extends HeritageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central);
        System.out.println(songArrayList);
    }
}
