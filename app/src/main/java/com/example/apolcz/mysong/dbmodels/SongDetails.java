package com.example.apolcz.mysong.dbmodels;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by apolcz on 15.08.2016.
 */
public class SongDetails implements Serializable {

    @DatabaseField(generatedId = true, columnName = "song_id")
    public int songId;

    @DatabaseField(columnName = "song_name")
    public String songName;

    @ForeignCollectionField(eager = true, foreignFieldName = "songDetails")
    public Collection<SongNoteDetails> songNotesList;


    public String getSongName() {
        return songName;
    }

    public SongDetails() {
        this.songNotesList =  new ArrayList<SongNoteDetails>() {
        };
    }
}

