package com.example.basketballcounter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView counterTeamA, counterTeamB;
    Button onePointTeamA, twoPointTeamA, threePointTeamA, onePointTeamB, twoPointTeamB, threePointTeamB, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inflate
        // Team A
        counterTeamA = findViewById(R.id.tv_counter_team_a);
        onePointTeamA = findViewById(R.id.bt_one_team_a);
        twoPointTeamA = findViewById(R.id.bt_two_team_a);
        threePointTeamA = findViewById(R.id.bt_three_team_a);
        // Team B
        counterTeamB = findViewById(R.id.tv_counter_team_b);
        onePointTeamB = findViewById(R.id.bt_one_team_b);
        twoPointTeamB = findViewById(R.id.bt_two_team_b);
        threePointTeamB = findViewById(R.id.bt_three_team_b);
        // Reset
        reset = findViewById(R.id.bt_reset);

        // Operations
        // Team A
        onePointTeamA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(counterTeamA.getText().toString());
                counterTeamA.setText((++value) + "");
            }
        });
        twoPointTeamA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(counterTeamA.getText().toString());
                counterTeamA.setText((value + 2) + "");
            }
        });
        threePointTeamA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(counterTeamA.getText().toString());
                counterTeamA.setText((value + 3) + "");
            }
        });

        // Team B
        onePointTeamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(counterTeamB.getText().toString());
                counterTeamB.setText((++value) + "");
            }
        });
        twoPointTeamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(counterTeamB.getText().toString());
                counterTeamB.setText((value + 2) + "");
            }
        });
        threePointTeamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(counterTeamB.getText().toString());
                counterTeamB.setText((value + 3) + "");
            }
        });

        // Reset
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterTeamA.setText("0");
                counterTeamB.setText("0");
            }
        });
    }
}