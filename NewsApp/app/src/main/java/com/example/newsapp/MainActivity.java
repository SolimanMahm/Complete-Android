package com.example.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.api.ApiInterface;
import com.example.newsapp.api.Articles;
import com.example.newsapp.api.News;
import com.example.newsapp.models.ArticleAdapter;
import com.example.newsapp.models.Category;
import com.example.newsapp.models.CategoryAdapter;
import com.example.newsapp.models.OnItemClickListener;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static final String API_KEY = "Insert Your API Key";

    private RecyclerView category_rv, articles_rv;


    private ArrayList<Category> categories = new ArrayList<>();
    private ArrayList<Articles> articles = new ArrayList<>();
    private ApiInterface apiInterface;

    private ProgressBar progressBar;
    private ArticleAdapter adapter1;
    OkHttpClient okHttpClient;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        category_rv = findViewById(R.id.category_rv);
        articles_rv = findViewById(R.id.articles_rv);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        category_rv.setHasFixedSize(true);
        category_rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        articles_rv.setHasFixedSize(true);
        articles_rv.setLayoutManager(new LinearLayoutManager(this));

        categories.add(new Category(R.drawable.business, "Business"));
        categories.add(new Category(R.drawable.entertaiment, "Entertainment"));
        categories.add(new Category(R.drawable.health, "Health"));
        categories.add(new Category(R.drawable.science, "Science"));
        categories.add(new Category(R.drawable.technology, "Technology"));
        categories.add(new Category(R.drawable.sports, "Sports"));
        categories.add(new Category(R.drawable.general, "General"));

        CategoryAdapter adapter = new CategoryAdapter(categories, this);

        category_rv.setAdapter(adapter);

        adapter1 = new ArticleAdapter(articles, this);

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder().
                baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

        progressBar.setVisibility(View.VISIBLE);
        getData("Business");
    }

    @Override
    public void onItemClickCategory(int position) {
        progressBar.setVisibility(View.VISIBLE);
        getData(categories.get(position).getText());
    }

    @Override
    public void onItemClickArticle(int position) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(articles.get(position).getUrl()));
        startActivity(intent);
    }

    public void getData(String q) {
        articles.clear();
        Call<News> call = apiInterface.getNews(q, API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful()) {
                    News news = response.body();
                    for (Articles article : news.getArticles()) {
                        articles.add(article);
                    }
                    articles_rv.setAdapter(adapter1);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}