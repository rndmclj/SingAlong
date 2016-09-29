package com.example.apolcz.mysong.dbmodels;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by apolcz on 25.09.2016.
 */
public class SongNotes {



        @DatabaseField(generatedId=true, useGetSet=true)
        private Long id;

        @DatabaseField
        private int type;

        @DatabaseField
        private String songTitle;


        @DatabaseField(foreign = true, foreignAutoRefresh= true)
        private SongDetails songDetails;
}
