package com.example.apolcz.mysong.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;

import com.example.apolcz.mysong.R;
import com.example.apolcz.mysong.dbmodels.NoteDetails;

import java.util.List;

/**
 * Created by apolcz on 15.08.2016.
 */
public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

    List<NoteDetails> noteList;
    private List<String> rgbList;
    private LayoutInflater layoutInflater;

    public CustomSpinnerAdapter(List<String> rgbList, Context context){
        this.rgbList=rgbList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return rgbList.size();
    }

    @Override
    public Object getItem(int position) {
        return rgbList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = layoutInflater.inflate(R.layout.spinner_color_display_layout,null);
        ImageView im = ((ImageView) view.findViewById(R.id.spinner_image));
        im.setBackgroundColor(rgbList.get(position).hashCode());
        return view;
    }




}