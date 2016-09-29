package com.example.apolcz.mysong.dbmodels;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by apolcz on 15.08.2016.
 */
public class SongDetails implements Serializable {

    @DatabaseField(generatedId = true, columnName = "song_id")
    public int songId;

    @DatabaseField(columnName = "song_name")
    public String songName;

//    @DatabaseField(dataType = DataType.SERIALIZABLE, columnName = "song_notes_list")
//    public SerializedList<SongNoteDetails> songNotesList;
@ForeignCollectionField(eager = true)
private Collection<NoteDetails> songNotesList;

//    public ArrayList<SongNoteDetails> getSongNotesList()
//    {
//        return songNotesList;
//    }
//
//    public void addToSongNotesList(SongNoteDetails songNote)
//    {
//        songNotesList.add(songNote);
//    }

    public String getSongName() {
        return songName;
    }

    public SongDetails(){
        this.songNotesList = new Collection<SongDetails>() {
        };
    }

//    public SongDetails(int songId, String songName, ArrayList<SongNoteDetails> songNotesList){
//        this.songId = songId;
//        this.songName = songName;
//        this.songNotesList = songNotesList;
//    }
}

