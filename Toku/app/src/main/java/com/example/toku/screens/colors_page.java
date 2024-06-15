package com.example.toku.screens;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.toku.R;
import com.example.toku.modules.item;
import com.example.toku.modules.itemAdapter;

import java.util.ArrayList;

public class colors_page extends AppCompatActivity {

    ListView lv;

    ArrayList<item> colors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page);

        lv = findViewById(R.id.listView);

        colors.add(new item(R.raw.black, R.drawable.color_black, "Kuro", "black"));
        colors.add(new item(R.raw.brown, R.drawable.color_brown, "Chairo", "brown"));
        colors.add(new item(R.raw.dusty_yellow, R.drawable.color_dusty_yellow, "Dasutiierō", "dusty yellow"));
        colors.add(new item(R.raw.gray, R.drawable.color_gray, "Gurē", "gray"));
        colors.add(new item(R.raw.green, R.drawable.color_green, "Midori", "green"));
        colors.add(new item(R.raw.red, R.drawable.color_red, "Aka", "red"));
        colors.add(new item(R.raw.white, R.drawable.color_white, "Shiro", "white"));
        colors.add(new item(R.raw.yellow, R.drawable.yellow, "Kiiro", "yellow"));

        itemAdapter adapter = new itemAdapter(this, R.layout.item_style, colors);
        adapter.setColor(getResources().getColor(R.color.colorsColor));

        lv.setAdapter(adapter);

    }
}