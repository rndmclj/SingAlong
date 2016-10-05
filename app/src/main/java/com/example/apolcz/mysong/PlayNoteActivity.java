package com.example.apolcz.mysong;

import android.content.Intent;
import android.os.Bundle;
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

        final SongDetails song = (SongDetails) getIntent().getSerializableExtra("SongDetails");
        final int noteIndex = (int) getIntent().getSerializableExtra("NoteIndex");

        // !! create the new array list in viewSongActivity
        ArrayList<SongNoteDetails> songNotes = new ArrayList<SongNoteDetails>(song.songNotesList);
        if (songNotes.size() <= noteIndex) {
            return;
        }

        SongNoteDetails songNote = songNotes.get(noteIndex);

        // !! stick this on screen for minutes:seconds
        NoteDetails note = songNote.note;
        noteToPlay.setText(note.getNoteName());
        noteToPlay.setBackgroundColor(note.getNoteColor().hashCode());

        int index = noteIndex + 1;

        Intent intent = new Intent(PlayNoteActivity.this, PlayNoteActivity.class);
        intent.putExtra("SongDetails", song);
        intent.putExtra("NoteIndex", index);
        startActivity(intent);
    }

}
