package com.example.apolcz.mysong.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.apolcz.mysong.R;
import com.example.apolcz.mysong.dbmodels.NoteDetails;
import com.example.apolcz.mysong.dbmodels.SongDetails;
import com.example.apolcz.mysong.dbmodels.SongNoteDetails;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by apolcz on 15.08.2016.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "notedir.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<NoteDetails, Integer> noteDao = null;
    private Dao<SongDetails, Integer> songDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
        try {

            // Create tables. This onCreate() method will be invoked only once of the application life time i.e. the first time when the application starts.
            TableUtils.createTable(connectionSource, NoteDetails.class);
            TableUtils.createTable(connectionSource, SongDetails.class);
            TableUtils.createTable(connectionSource, SongNoteDetails.class);

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to create databases", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
        try {

            // In case of change in database of next version of application, please increase the value of DATABASE_VERSION variable, then this method will be invoked
            //automatically. Developer needs to handle the upgrade logic here, i.e. create a new table or a new column to an existing table, take the backups of the
            // existing database etc.

            TableUtils.dropTable(connectionSource, NoteDetails.class, true);
            onCreate(sqliteDatabase, connectionSource);

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version " + oldVer + " to new "
                    + newVer, e);
        }
    }

    public Dao<NoteDetails, Integer> getNoteDao() throws SQLException {
        if (noteDao  == null) {
            noteDao  = getDao(NoteDetails.class);
        }
        return noteDao ;
    }

    public Dao<SongDetails, Integer> getSongDao() throws SQLException {
        if (songDao == null) {
            songDao = getDao(SongDetails.class);
        }
        return songDao ;
    }
}
