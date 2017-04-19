package com.dragonfury.duy.p4a14nguyendennisanimatedgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Android lifecycle
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this)); //Instantiate DrawView
    }
}
