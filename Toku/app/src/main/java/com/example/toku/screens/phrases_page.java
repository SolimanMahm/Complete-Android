package com.example.toku.screens;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.toku.R;
import com.example.toku.modules.item;
import com.example.toku.modules.itemAdapter;

import java.util.ArrayList;

public class phrases_page extends AppCompatActivity {

    ListView lv;

    ArrayList<item> phrases = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page);

        lv = findViewById(R.id.listView);

        phrases.add(new item(R.raw.are_you_coming, "Kimasu ka?", "Are you coming?"));
        phrases.add(new item(R.raw.dont_forget_to_subscribe, "Kōdoku suru koto o wasurenaide kudasai", "don't forget to subscribe"));
        phrases.add(new item(R.raw.how_are_you_feeling, "Go kibun wa ikagadesu ka?", "How are you feeling?"));
        phrases.add(new item(R.raw.i_love_anime, "watashi wa anime ga daisukidesu", "i love anime"));
        phrases.add(new item(R.raw.i_love_programming, "watashi wa puroguramingu ga daisukidesu", "i love programming"));
        phrases.add(new item(R.raw.programming_is_easy, "puroguramingu wa kantandesu", "programming is easy"));
        phrases.add(new item(R.raw.what_is_your_name, "Anata no namae wa nandesuka", "What is your name?"));
        phrases.add(new item(R.raw.where_are_you_going, "Doko ni iku no", "Where are you going?"));
        phrases.add(new item(R.raw.yes_im_coming, "hai, ikimasu", "yes, I'm coming"));

        itemAdapter adapter = new itemAdapter(this, R.layout.item_style, phrases);
        adapter.setColor(getResources().getColor(R.color.phrasesColor));

        lv.setAdapter(adapter);

    }

}