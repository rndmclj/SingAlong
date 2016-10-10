package com.example.apolcz.mysong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.apolcz.mysong.adapters.NotesSpinnerAdapter;
import com.example.apolcz.mysong.db.DatabaseHelper;
import com.example.apolcz.mysong.dbmodels.NoteDetails;
import com.example.apolcz.mysong.dbmodels.SongDetails;
import com.example.apolcz.mysong.dbmodels.SongNoteDetails;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class SongActivity extends AppCompatActivity implements View.OnClickListener{

    Spinner noteView;

    private DatabaseHelper databaseHelper = null;
    private Dao<SongDetails, Integer> songDao;
    private Dao<NoteDetails, Integer> noteDao;
    private List<NoteDetails> noteList;

    Button saveSong;
    Button nextNote;
    TextView songTitle;
    Spinner minuteDropdown;
    Spinner secondsDropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_song);
        songTitle = (((TextView) findViewById(R.id.noteTitle)));
        saveSong =((Button) findViewById(R.id.saveSong));
        nextNote =((Button) findViewById(R.id.nextNote));
        noteView = (Spinner) findViewById(R.id.notes);
        minuteDropdown =(Spinner) findViewById(R.id.minuteTime);
        secondsDropdown =(Spinner) findViewById(R.id.secondsTime);

        PopulateTimeSpinner(minuteDropdown);
        PopulateTimeSpinner(secondsDropdown);

        //final Bundle extras = getIntent().getExtras();
        final SongDetails song = (SongDetails) getIntent().getSerializableExtra("SongDetails");
        String songTitleValue = song.songName;
        songTitle.setText(songTitleValue);

        saveSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SongNoteDetails songNote = getSongNoteDetails(song);
                song.songNotesList.add(songNote);

                try {
                    final Dao<SongDetails, Integer> songDao = getHelper().getSongDao();
                    songDao.create(song);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                for (SongNoteDetails _songNote : song.songNotesList) {
                    try {
                        final Dao<SongNoteDetails, Integer> songNoteDao = getHelper().getSongNoteDao();
                        songNoteDao.create(_songNote);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                Intent intent = new Intent(SongActivity.this, ViewSongActivity.class);
                startActivity(intent);
            };
        });
        nextNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SongNoteDetails songNote = getSongNoteDetails(song);
                song.songNotesList.add(songNote);
                Intent intent = new Intent(SongActivity.this, SongActivity.class);
                intent.putExtra("SongDetails", song);
                startActivity(intent);
                finish();
            };
        });

        try {
            getNotesDB();
        }
        catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }


    }

    @NonNull
    private SongNoteDetails getSongNoteDetails(SongDetails songDetails) {
        SongNoteDetails songNoteDetails  = new SongNoteDetails();
        songNoteDetails.note =(NoteDetails) noteView.getSelectedItem();
        songNoteDetails.minutes = (int) minuteDropdown.getSelectedItem();
        songNoteDetails.seconds = (int) secondsDropdown.getSelectedItem();
        songNoteDetails.songDetails = songDetails;
        songNoteDetails.note.songNoteDetails = songNoteDetails;
        return songNoteDetails;
    }

    private void PopulateTimeSpinner(Spinner timeSpinner) {
        Integer[] numbers = new Integer[60];
        for (int i = 0; i<numbers.length; i++) {
            numbers[i] = i ;
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, numbers);
        timeSpinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
            final SongDetails songDetails = new SongDetails();
            songDetails.songName = songTitle.getText().toString();
            try {
                Dao<SongDetails, Integer> songDao = new DatabaseHelper(this).getSongDao();
                songDao.create(songDetails);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // save last note in db!

            Intent intent = new Intent(SongActivity.this, HomePageActivity.class);
            startActivity(intent);
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this,DatabaseHelper.class);
        }
        return databaseHelper;
    }

    private void getNotesDB() throws SQLException, InterruptedException {
        noteDao = getHelper().getNoteDao();
        noteList = noteDao.queryForAll();
        if(noteList.size()>0) {
            NotesSpinnerAdapter notesDropdown = new NotesSpinnerAdapter(noteList,this);
            noteView.setAdapter(notesDropdown);
        }
    }

}
