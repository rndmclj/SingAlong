package com.example.apolcz.mysong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePageActivity extends AppCompatActivity {

    Button newSongButton;
    Button noteButton;
    Button loadSongsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        newSongButton =((Button) findViewById(R.id.newSong));
        newSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, SongTitleActivity.class);
                startActivity(intent);
            };
        });

        noteButton = ((Button) findViewById(R.id.showNotes));
        noteButton.setOnClickListener(new View.OnClickListener(){
          @Override
            public void onClick(View v){
              Intent intent = new Intent(HomePageActivity.this, ViewNoteActivity.class);
              startActivity(intent);
          }
        });

        loadSongsButton = ((Button) findViewById(R.id.loadSong));
        loadSongsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(HomePageActivity.this, TestSoundActivity.class);
                startActivity(intent);
            }
        });
    }
}