package com.example.tunesplayer;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tunesplayer.models.MyAdapter;
import com.example.tunesplayer.models.tune;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;

    ArrayList<tune> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        lv = findViewById(R.id.listView);

        data.add(new tune(R.raw.note1, getResources().getColor(R.color.oneColor)));
        data.add(new tune(R.raw.note2, getResources().getColor(R.color.twoColor)));
        data.add(new tune(R.raw.note3, getResources().getColor(R.color.threeColor)));
        data.add(new tune(R.raw.note4, getResources().getColor(R.color.fourColor)));
        data.add(new tune(R.raw.note5, getResources().getColor(R.color.fiveColor)));
        data.add(new tune(R.raw.note6, getResources().getColor(R.color.sixColor)));
        data.add(new tune(R.raw.note7, getResources().getColor(R.color.sevenColor)));

        MyAdapter adapter = new MyAdapter(this, R.layout.tune_style, data);
        adapter.setHeight(screenHeight - 168); // NO SCROLL

        lv.setAdapter(adapter);

    }
}