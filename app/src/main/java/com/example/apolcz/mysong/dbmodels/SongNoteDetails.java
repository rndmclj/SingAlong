package com.example.apolcz.mysong.dbmodels;

        import com.example.apolcz.mysong.dbmodels.NoteDetails;
        import com.j256.ormlite.field.DatabaseField;

        import java.io.Serializable;
        import java.util.List;

/**
 * Created by apolcz on 15.08.2016.
 */
public class SongNoteDetails implements Serializable {



    public NoteDetails note;
    public int minutes;
    public int seconds;

    public SongNoteDetails(){}

    public SongNoteDetails(NoteDetails note, int minutes, int seconds){
        this.note = note;
        this.minutes = minutes;
        this.seconds = seconds;
    }
}
