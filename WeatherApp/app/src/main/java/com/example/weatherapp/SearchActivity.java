package com.example.weatherapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SearchActivity extends AppCompatActivity {


    Toolbar toolbar;
    AppBarLayout barLayout;
    TextInputLayout inputLayout;
    TextInputEditText search;

    private Intent intent;
    private int color;

    public static final String LOCATION = "Location";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toolbar = findViewById(R.id.toolBar);
        search = findViewById(R.id.textInputEditText);
        barLayout = findViewById(R.id.appBarLayoutSearch);
        inputLayout = findViewById(R.id.textInputLayout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        intent = getIntent();
        color = intent.getIntExtra("color", R.color.toolBar);

        barLayout.setBackgroundColor(getResources().getColor(color));
        inputLayout.setBoxStrokeColor(getResources().getColor(color));
        inputLayout.setHintTextColor(ColorStateList.valueOf(getResources().getColor(color)));
        getWindow().setStatusBarColor(getResources().getColor(color));

        search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    search.setHint("Enter City Name");
                } else search.setHint("");
            }
        });


        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    intent.putExtra(LOCATION, textView.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                    return true;
                }
                return false;
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


}