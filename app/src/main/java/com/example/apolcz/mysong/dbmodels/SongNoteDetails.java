package com.example.apolcz.mysong.dbmodels;

        import com.j256.ormlite.field.DatabaseField;

        import java.io.Serializable;

/**
 * Created by apolcz on 15.08.2016.
 */
public class SongNoteDetails implements Serializable {

    @DatabaseField(generatedId=true, columnName = "song_note_id")
    private int songNoteId;

    @DatabaseField(columnName = "minutes")
    public int minutes;

    @DatabaseField(columnName = "seconds")
    public int seconds;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh= true)
    public NoteDetails note;

    @DatabaseField(foreign = true, foreignAutoRefresh= true, foreignAutoCreate = true)
    public SongDetails songDetails;

    public SongNoteDetails(){}

    public SongNoteDetails(NoteDetails note, int minutes, int seconds){
        this.note = note;
        this.minutes = minutes;
        this.seconds = seconds;
    }
}
