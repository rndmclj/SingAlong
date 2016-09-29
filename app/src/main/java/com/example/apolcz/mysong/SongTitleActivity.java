package com.example.apolcz.mysong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.apolcz.mysong.db.DatabaseHelper;
import com.example.apolcz.mysong.dbmodels.SongDetails;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.sql.SQLOutput;

/**
 * Created by apolcz on 13.08.2016.
 */
public class SongTitleActivity extends AppCompatActivity implements View.OnClickListener {

    EditText songTitle;
    Button createSong;
    final Bundle bundle = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_title_page);

        createSong = ((Button)findViewById(R.id.nextButton));
        songTitle = ((EditText) findViewById(R.id.songTitle));
        createSong.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.nextButton){

            //bundle.putString("songTitle",songTitle.getText().toString());
            Intent intent = new Intent(this, SongActivity.class);
            //intent.putExtras(bundle);
            SongDetails song = new SongDetails();
            song.songName = songTitle.getText().toString();
            intent.putExtra("SongDetails", song);
            startActivity(intent);
            reset();
        }
    }

    private void reset() {
        songTitle.setText("");
    }
}
