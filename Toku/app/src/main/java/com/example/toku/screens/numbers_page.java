package com.example.toku.screens;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.toku.modules.itemAdapter;
import com.example.toku.R;
import com.example.toku.modules.item;

import java.util.ArrayList;

public class numbers_page extends AppCompatActivity {

    ListView listView;

    ArrayList<item> numbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page);

        listView = findViewById(R.id.listView);

        numbers.add(new item(R.raw.number_one_sound, R.drawable.number_one, "Ichi", "One"));
        numbers.add(new item(R.raw.number_two_sound, R.drawable.number_two, "Ni", "Two"));
        numbers.add(new item(R.raw.number_three_sound, R.drawable.number_three, "San", "Three"));
        numbers.add(new item(R.raw.number_four_sound, R.drawable.number_four, "Shi", "Four"));
        numbers.add(new item(R.raw.number_five_sound, R.drawable.number_five, "Go", "Five"));
        numbers.add(new item(R.raw.number_six_sound, R.drawable.number_six, "Roku", "Six"));
        numbers.add(new item(R.raw.number_seven_sound, R.drawable.number_seven, "Sebun", "Seven"));
        numbers.add(new item(R.raw.number_eight_sound, R.drawable.number_eight, "Hachi", "Eight"));
        numbers.add(new item(R.raw.number_nine_sound, R.drawable.number_nine, "Kyū", "Nine"));
        numbers.add(new item(R.raw.number_ten_sound, R.drawable.number_ten, "Jū", "Ten"));

        itemAdapter adapter = new itemAdapter(this, R.layout.item_style, numbers);
        adapter.setColor(getResources().getColor(R.color.numbersColor));

        listView.setAdapter(adapter);

    }
}