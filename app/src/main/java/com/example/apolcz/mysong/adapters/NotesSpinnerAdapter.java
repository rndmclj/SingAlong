
package com.example.apolcz.mysong.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.apolcz.mysong.R;
import com.example.apolcz.mysong.dbmodels.NoteDetails;

import java.util.List;

/**
 * Created by apolcz on 15.08.2016.
 */
public class NotesSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

    List<NoteDetails> noteList;
    //private List<String> rgbList;
    private LayoutInflater layoutInflater;

    public NotesSpinnerAdapter(List<NoteDetails> noteList, Context context){
        this.noteList = noteList;
        layoutInflater = LayoutInflater.from(context);
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
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = layoutInflater.inflate(R.layout.spinner_note_display_layout,null);
        TextView text = (TextView) view.findViewById(R.id.textView);

        if (noteList.get(position) != null){
            text.setText(noteList.get(position).getNoteName());
            text.setBackgroundColor(noteList.get(position).getNoteColor().hashCode());
        }
        return view;
    }




}