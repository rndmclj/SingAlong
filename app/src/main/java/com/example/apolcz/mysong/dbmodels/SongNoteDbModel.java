package com.example.apolcz.mysong.dbmodels;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

//com.example.apolcz.mysong.dbmodels;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by apolcz on 15.08.2016.
 */
public class SongNoteDbModel implements Serializable {

    @DatabaseField(generatedId = true, columnName = "song_id")
    public int songId;
    @DatabaseField(columnName = "song_name")
    public String songName;

    public String getSongName() {
        return songName;
    }

//    public SongDetails(){}
//
//    public SongDetails(int songId, String songName){
//        this.songId = songId;
//        this.songName = songName;
//    }
}
