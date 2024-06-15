package com.example.toku.modules;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.toku.R;

import java.util.ArrayList;

public class itemAdapter extends BaseAdapter {

    private Context context;
    private int resource, color;


    private ArrayList<item> data;

    private MediaPlayer mediaPlayer;

    public itemAdapter(Context context, int resource, ArrayList<item> data) {
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public item getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(context).inflate(resource, null, false);
        }

        LinearLayout base = view.findViewById(R.id.base);
        ImageView image = view.findViewById(R.id.image);
        TextView jpName = view.findViewById(R.id.jpName);
        TextView enName = view.findViewById(R.id.enName);
        ImageButton sound = view.findViewById(R.id.sound);

        base.setBackgroundColor(color);
        jpName.setText(getItem(i).getJpName());
        enName.setText(getItem(i).getEnName());
        if (getItem(i).getImage() == 0) image.setVisibility(View.GONE);
        else image.setImageResource(getItem(i).getImage());

        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer = MediaPlayer.create(context, getItem(i).getSound());
                mediaPlayer.start();
            }
        });

        return view;
    }
}
