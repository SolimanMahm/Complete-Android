package com.example.tunesplayer.models;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tunesplayer.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private int resources;
    private int height;

    private MediaPlayer mediaPlayer;

    private ArrayList<tune> data;

    public MyAdapter(Context context, int resources, ArrayList<tune> data) {
        this.context = context;
        this.resources = resources;
        this.data = data;
    }

    public void setHeight(int height) {
        if (getCount() > 0) height /= getCount();
        this.height = height;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public tune getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(resources, null, false);
        }

        TextView tv = view.findViewById(R.id.tvOne);

        tv.setHeight(height);
        tv.setBackgroundColor(getItem(i).getColor());

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer = MediaPlayer.create(context, getItem(i).getSound());
                mediaPlayer.start();
            }
        });

        return view;

    }
}
