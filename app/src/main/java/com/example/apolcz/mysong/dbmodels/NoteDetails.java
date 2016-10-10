package com.example.apolcz.mysong.dbmodels;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by apolcz on 15.08.2016.
 */
public class NoteDetails implements Serializable {

    @DatabaseField(generatedId = true, columnName = "note_id")
    public int noteId;
    @DatabaseField(columnName = "note_color")
    public String noteColor;
    @DatabaseField( columnName = "note_name")
    public String noteName;
    @DatabaseField(foreign = true, foreignAutoRefresh= true)
    public SongNoteDetails songNoteDetails;

    public String getNoteName() {
        return noteName;
    }

    public String getNoteColor() {
        return noteColor;
    }

    public NoteDetails() {

    }

    public NoteDetails(int noteId, String noteName, String noteColor) {
        this.noteColor = noteColor;
        this.noteId = noteId;
        this.noteName = noteName;

    }


}
