package com.example.apolcz.mysong;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.apolcz.mysong.listeners.Listener;

/**
 * Created by apolcz on 12.11.2016.
 */
public class TestSoundActivity extends AppCompatActivity {


    TextView testView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        Listener testListen = new Listener();
        testListen.startRecording();
    }
}
