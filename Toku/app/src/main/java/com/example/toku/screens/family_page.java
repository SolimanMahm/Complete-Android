package com.example.toku.screens;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.toku.R;
import com.example.toku.modules.item;
import com.example.toku.modules.itemAdapter;

import java.util.ArrayList;

public class family_page extends AppCompatActivity {

    ListView lv;

    ArrayList<item> family = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page);

        lv = findViewById(R.id.listView);

        family.add(new item(R.raw.father, R.drawable.family_father, "Chichioya", "father"));
        family.add(new item(R.raw.daughter, R.drawable.family_daughter, "Musume", "daughter"));
        family.add(new item(R.raw.grand_father, R.drawable.family_grandfather, "Sofu", "grandfather"));
        family.add(new item(R.raw.grand_mother, R.drawable.family_grandmother, "Sobo", "grandmother"));
        family.add(new item(R.raw.mother, R.drawable.family_mother, "Hahaoya", "mother"));
        family.add(new item(R.raw.older_bother, R.drawable.family_older_brother, "Ani", "older brother"));
        family.add(new item(R.raw.older_sister, R.drawable.family_older_sister, "Ane", "older sister"));
        family.add(new item(R.raw.son, R.drawable.family_son, "Musuko", "son"));
        family.add(new item(R.raw.younger_brohter, R.drawable.family_younger_brother, "Otōto", "younger brother"));
        family.add(new item(R.raw.younger_sister, R.drawable.family_younger_sister, "Imōto", "younger sister"));

        itemAdapter adapter = new itemAdapter(this, R.layout.item_style, family);
        adapter.setColor(getResources().getColor(R.color.familyColor));

        lv.setAdapter(adapter);

    }
}