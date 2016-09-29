package com.example.apolcz.mysong;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.apolcz.mysong.adapters.CustomSpinnerAdapter;
import com.example.apolcz.mysong.db.DatabaseHelper;
import com.example.apolcz.mysong.dbmodels.NoteDetails;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity implements View.OnClickListener{

    Spinner noteColorPicker;
    EditText noteNameField;
    private Button saveNewNote;

    private DatabaseHelper databaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_note);

        saveNewNote =((Button) findViewById(R.id.saveNewNote));
        noteNameField = ((EditText) findViewById(R.id.noteTitle));
        noteColorPicker = ((Spinner) findViewById(R.id.colorPicker));

        saveNewNote.setOnClickListener(this);

        List<String> spinnerArray =  new ArrayList<>();

        spinnerArray.add("#00ffff");
        spinnerArray.add("#ccff99");
        spinnerArray.add("#ff9900");
        spinnerArray.add("#ff00ff");
        spinnerArray.add("#ff9900");
        spinnerArray.add("#00cc00");

        CustomSpinnerAdapter customAdapter = new CustomSpinnerAdapter(spinnerArray,getApplicationContext());
        noteColorPicker.setAdapter(customAdapter);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.saveNewNote){
            final NoteDetails noteDetails = new NoteDetails();
            noteDetails.noteName= noteNameField.getText().toString();
            noteDetails.noteColor = noteColorPicker.getSelectedItem().toString();
            try {
                final Dao<NoteDetails, Integer> noteDao = getHelper().getNoteDao();
                noteDao.create(noteDetails);
                reset();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    private void reset()
    {
       noteNameField.setText("");
    }
}