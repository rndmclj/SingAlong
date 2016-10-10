package com.example.apolcz.mysong;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.apolcz.mysong.dbmodels.NoteDetails;
import com.example.apolcz.mysong.dbmodels.SongDetails;
import com.example.apolcz.mysong.dbmodels.SongNoteDetails;

import java.util.ArrayList;

/**
 * Created by apolcz on 05.10.2016.
 */
public class PlayNoteActivity extends AppCompatActivity {
    TextView noteToPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_to_play);
        noteToPlay = ((TextView) findViewById(R.id.playNote));

        final ArrayList<SongNoteDetails> songNotes = (ArrayList<SongNoteDetails>) getIntent().getSerializableExtra("SongNotes");
        final int noteIndex = (int) getIntent().getSerializableExtra("NoteIndex");

        if (songNotes.size() <= noteIndex) {
            Intent intent = new Intent(PlayNoteActivity.this, ViewSongActivity.class);
            startActivity(intent);
            return;
        }

        SongNoteDetails songNote = songNotes.get(noteIndex);

        // !! stick this on screen for minutes:seconds
        NoteDetails note = songNote.note;
        noteToPlay.setText(note.getNoteName());
        noteToPlay.setBackgroundColor(note.getNoteColor().hashCode());

        final int index = noteIndex + 1;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(PlayNoteActivity.this, PlayNoteActivity.class);
                intent.putExtra("SongNotes", songNotes);
                intent.putExtra("NoteIndex", index);
                startActivity(intent);
            }
        }, songNote.seconds*1000+songNote.minutes*60*1000);
    }
}
