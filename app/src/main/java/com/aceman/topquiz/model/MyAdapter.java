package com.aceman.topquiz.model;

import android.content.Context;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aceman.topquiz.R;
import com.google.common.collect.Ordering;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lionel JOFFRAY - on 23/01/2019.
 */
public class MyAdapter extends BaseAdapter {

    public static boolean byName;
    public static boolean byScore;
    private Context context;
    private LayoutInflater inflater;
    private List<SaveList> mPlayerListRank;

    public MyAdapter(Context context, List<SaveList> PlayerListRank){
        this.context = context;
        this.mPlayerListRank = PlayerListRank;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if(byScore)
            return 5;
        else
            return mPlayerListRank.size();
    }

    @Override
    public Object getItem(int position) {
        return mPlayerListRank.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(byName)
        Collections.sort(mPlayerListRank, new SortByName());
        if(byScore)
        Collections.sort(mPlayerListRank, new SortByScore());

        convertView =inflater.inflate(R.layout.rank_listview,parent,false);

        final SaveList actualItem =(SaveList) getItem(position);
        String Name = actualItem.getName();
        int Score = actualItem.getScore();
            TextView NameView = convertView.findViewById(R.id.rank_name);
        // TextView ScoreView = convertView.findViewById(R.id.rank_score);
       TextView Titre = convertView.findViewById(R.id.rank_titre);
       LinearLayout Second = convertView.findViewById(R.id.second_layout_rank);
            TextView ByName = convertView.findViewById(R.id.rank_by_name);
            TextView ByScore = convertView.findViewById(R.id.rank_by_score);

            Titre.setVisibility(View.INVISIBLE);
            Second.setVisibility(View.INVISIBLE);

            NameView.setText(Name+"     "+Integer.toString(Score));
            return convertView;
    }
}
