package com.example.apolcz.mysong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.apolcz.mysong.adapters.CustomListAdapter;
import com.example.apolcz.mysong.adapters.CustomListSongAdapter;
import com.example.apolcz.mysong.db.DatabaseHelper;
import com.example.apolcz.mysong.dbmodels.NoteDetails;
import com.example.apolcz.mysong.dbmodels.SongDetails;
import com.example.apolcz.mysong.dbmodels.SongNoteDetails;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apolcz on 13.08.2016.
 */
public class ViewSongActivity extends AppCompatActivity {
    private List<SongDetails> songList;
    private Dao<SongDetails, Integer> songDao;
    CustomListSongAdapter customListAdapter;
    private DatabaseHelper databaseHelper = null;
    ListView songView;
    Button playSong;
    SongDetails selectedSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list_page);
        songView = (ListView) findViewById(R.id.songList);
        playSong = (Button) findViewById(R.id.playSong);
        songView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        try {
            getSongsDB();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        playSong.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ViewSongActivity.this, PlayNoteActivity.class);
                intent.putExtra("SongNotes", new ArrayList<SongNoteDetails>(selectedSong.songNotesList));
                intent.putExtra("NoteIndex", 0);
                startActivity(intent);
            }
        });
        songView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
                selectedSong  = songList.get(position);
            }
        });
    }

    private void getSongsDB() throws SQLException, InterruptedException {
        songDao = getHelper().getSongDao();
        songList = songDao.queryForAll();
        if(songList.size()>0) {
            customListAdapter = new CustomListSongAdapter(this, R.layout.song_list_page, songList);
            songView.setAdapter(customListAdapter);
        }
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this,DatabaseHelper.class);
        }
        return databaseHelper;
    }
}