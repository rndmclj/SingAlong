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
        import com.example.apolcz.mysong.dbmodels.SongDetails;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by apolcz on 17.08.2016.
 */
public class CustomListSongAdapter extends ArrayAdapter {

    private Context mContext;
    private int id;

    List<SongDetails> songList;
    List<String> songNames;
    private LayoutInflater layoutInflater;

    public CustomListSongAdapter(Context context,int textViewResourceId, List<SongDetails> songList) {
        super(context, textViewResourceId, songList);
        mContext = context;
        id = textViewResourceId;
        this.layoutInflater = LayoutInflater.from(context);

        this.songList = songList;
        this.songNames = getSongNamesValues(songList);
    }

    @Override
    public int getCount() {
        return songList.size();
    }

    @Override
    public Object getItem(int position) {
        return songList.get(position);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = layoutInflater.inflate(R.layout.list_display_custom, null);
        TextView text = (TextView) view.findViewById(R.id.textView);

        if (songNames.get(position) != null){
            text.setText(songNames.get(position));
        }
        return view;
    }

    private List<String> getSongNamesValues(List<SongDetails> songList) {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            names.add(i, songList.get(i).getSongName());
        }
        return names;
    }

}
