package com.example.apolcz.mysong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.apolcz.mysong.adapters.CustomListAdapter;
import com.example.apolcz.mysong.db.DatabaseHelper;
import com.example.apolcz.mysong.dbmodels.NoteDetails;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewNoteActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseHelper databaseHelper = null;
    private int selectedRecordPosition = -1;// This holds the value of the row number, which user has selected for further action
    private Dao<NoteDetails, Integer> noteDao;
    private List<NoteDetails> noteList;
    CustomListAdapter customListAdapter;


    private ListView noteView;
    private Button newNoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_note_page);
        noteView = (ListView) findViewById(R.id.noteList);
        newNoteButton =((Button) findViewById(R.id.createNewNote));
        newNoteButton.setOnClickListener(this);
        try {
            getNotesDB();
        }
            catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    private List<String> getNoteNamesValues(List<NoteDetails> noteList) {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < noteList.size(); i++) {
            names.add(i, noteList.get(i).getNoteName());
        }
        return names;
    }
    @NonNull
    private List<String> getNoteRgbValues(List<NoteDetails> noteList) {
        List<String> colors = new ArrayList<>();
        for(int i = 0;i<noteList.size();i++){
            colors.add(i,noteList.get(i).getNoteColor());
        }
        return colors;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.createNewNote){
            Intent intent = new Intent(ViewNoteActivity.this, NoteActivity.class);
            startActivity(intent);
        }
    }



    @Override
    public void onResume() {
        super.onResume();
        //am pus iful asta mai sus, era inainte de notifyDataSetCHanged();

        try {
            getNotesDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (noteList.size() > 0) {
            customListAdapter.notifyDataSetChanged();
        }
    }

    private void getNotesDB() throws SQLException, InterruptedException {
        noteDao = getHelper().getNoteDao();
        noteList = noteDao.queryForAll();
        if(noteList.size()>0) {

            //aici trebuie customizat adaptorul astfel incat sa afiseze textul noteName si text.background noteColor.
            //am cam scris cum sa ia valorile, se vede in consola cand dai run ca se printeaza culorile cum trebuie
            //dar nu stiu cum sa pun layout-ul :(

            customListAdapter = new CustomListAdapter(this, R.layout.display_note_page, noteList);
            noteView.setAdapter(customListAdapter);
        }
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this,DatabaseHelper.class);
        }
        return databaseHelper;
    }

}