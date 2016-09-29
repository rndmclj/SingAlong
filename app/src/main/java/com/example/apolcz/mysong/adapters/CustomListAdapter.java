package com.example.apolcz.mysong.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.apolcz.mysong.R;
import com.example.apolcz.mysong.dbmodels.NoteDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apolcz on 17.08.2016.
 */
public class CustomListAdapter extends ArrayAdapter {

    private Context mContext;
    private int id;

    List<NoteDetails> noteList;
    List<String> noteNames;
    List<String> rgbList;
    private LayoutInflater layoutInflater;

    public CustomListAdapter(Context context,int textViewResourceId, List<NoteDetails> noteList) {
        super(context, textViewResourceId, noteList);
        mContext = context;
        id = textViewResourceId;
        this.layoutInflater = LayoutInflater.from(context);

        this.noteList = noteList;
        this.rgbList = getNoteRgbValues(noteList);
        this.noteNames = getNoteNamesValues(noteList);
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int position) {
        return noteList.get(position);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = layoutInflater.inflate(R.layout.list_display_custom, null);
        TextView text = (TextView) view.findViewById(R.id.textView);

        if (noteNames.get(position) != null){
            text.setText(noteNames.get(position));
            text.setBackgroundColor(rgbList.get(position).hashCode());
        }
        return view;
    }

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
}
