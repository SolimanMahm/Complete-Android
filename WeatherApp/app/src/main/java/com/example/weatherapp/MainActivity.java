package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.bumptech.glide.Glide;
import com.example.weatherapp.API.WeatherModel;
import com.example.weatherapp.API.WeatherServices;
import com.google.android.material.appbar.AppBarLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    AppBarLayout barLayout;
    LinearLayout noWeather, showWeather;
    TextView location, time, temp, maxTemp, minTemp, state;
    ProgressBar progressBar;
    ImageView icon;

    private int SEARCH_RESULT = 10;

    private int background = R.color.toolBar, color = R.color.toolBar;
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static final String API_KEY = "INSERT YOUR API KEY";
    OkHttpClient okHttpClient;
    Retrofit retrofit;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    private WeatherServices weatherServices;
    private WeatherModel weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolBar);
        barLayout = findViewById(R.id.appBarLayoutMain);
        progressBar = findViewById(R.id.progressBar);
        noWeather = findViewById(R.id.noWeather);
        showWeather = findViewById(R.id.showWeather);
        location = findViewById(R.id.location);
        time = findViewById(R.id.time);
        temp = findViewById(R.id.temp);
        maxTemp = findViewById(R.id.maxTemp);
        minTemp = findViewById(R.id.minTemp);
        state = findViewById(R.id.state);
        icon = findViewById(R.id.imageView);

        showWeather.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        setSupportActionBar(toolbar);

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherServices = retrofit.create(WeatherServices.class);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search) {
            Intent intent = new Intent(getBaseContext(), SearchActivity.class);
            intent.putExtra("color", color);
            startActivityForResult(intent, SEARCH_RESULT);
            progressBar.setVisibility(View.GONE);
            noWeather.setVisibility(View.VISIBLE);
            return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        showWeather.setVisibility(View.GONE);
        if (requestCode == SEARCH_RESULT && resultCode == RESULT_OK) {
            noWeather.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            getData(data.getStringExtra(SearchActivity.LOCATION));
        }
    }

    private void reFactorUI() {

        getTheme(weather.getWeather().get(0).getMain());

        location.setText(weather.getName());
        time.setText("updated at: " + sdf.format(new Date()));
        temp.setText(weather.getMain().getTemp() + "");
        maxTemp.setText("MaxTemp: " + weather.getMain().getTemp_max());
        minTemp.setText("MinTemp: " + weather.getMain().getTemp_min());
        state.setText(weather.getWeather().get(0).getDescription());
        Glide.with(this).load(weather.getWeather().get(0).getIcon()).centerCrop().into(icon);

        progressBar.setVisibility(View.GONE);
        showWeather.setVisibility(View.VISIBLE);
    }

    private void getData(String location) {
        Call<WeatherModel> call = weatherServices.getWeather(location, API_KEY);
        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.isSuccessful()) {
                    weather = response.body();
                    reFactorUI();
                } else {
                    showWeather.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    noWeather.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable throwable) {
                Log.d("API", throwable.getMessage());
            }
        });
    }

    private void getTheme(@NonNull String condition) {
        switch (condition) {
            case "Thunderstorm":
                background = R.drawable.gradiant_thunderstorm;
                color = R.color.thunderstorm;
                break;
            case "Drizzle":
                background = R.drawable.gradiant_drizzle;
                color = R.color.drizzle;
                break;
            case "Rain":
                background = R.drawable.gradiant_rain;
                color = R.color.rain;
                break;
            case "Snow":
                background = R.drawable.gradiant_snow;
                color = R.color.snow;
                break;
            case "Mist":
                background = R.drawable.gradiant_mist;
                color = R.color.mist;
                break;
            case "Smoke":
                background = R.drawable.gradiant_smoke;
                color = R.color.smoke;
                break;
            case "Dust":
                background = R.drawable.gradiant_dust;
                color = R.color.dust;
                break;
            case "Fog":
                background = R.drawable.gradiant_fog;
                color = R.color.fog;
                break;
            case "Sand":
                background = R.drawable.gradiant_sand;
                color = R.color.sand;
                break;
            case "Ash":
                background = R.drawable.gradiant_ash;
                color = R.color.ash;
                break;
            case "Squall":
                background = R.drawable.gradiant_squall;
                color = R.color.squall;
                break;
            case "Haze":
                background = R.drawable.gradiant_haze;
                color = R.color.haze;
                break;
            case "Tornado":
                background = R.drawable.gradiant_tornado;
                color = R.color.tornado;
                break;
            case "Clear":
                background = R.drawable.gradiant_clear;
                color = R.color.clear;
                break;
            case "Clouds":
                background = R.drawable.gradiant_clouds;
                color = R.color.clouds;
                break;
        }
        showWeather.setBackgroundResource(background);
        barLayout.setBackgroundColor(getResources().getColor(color));
        getWindow().setStatusBarColor(getResources().getColor(color));
    }
}