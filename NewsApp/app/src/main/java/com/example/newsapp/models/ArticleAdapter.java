package com.example.newsapp.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapp.R;
import com.example.newsapp.api.Articles;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private ArrayList<Articles> articles = new ArrayList<>();
    private Context context;
    private OnItemClickListener listener;

    public ArticleAdapter(ArrayList<Articles> articles, OnItemClickListener listener) {
        this.articles = articles;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_view, parent, false);
        context = parent.getContext();
        return new ArticleViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Articles article = articles.get(position);
        Glide.with(context).load(article.getUrlToImage()).into(holder.image);
        holder.title.setText(article.getTitle());
        holder.subTitle.setText(article.getDescription());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {

        private CardView item;
        private ImageView image;
        private TextView title, subTitle;

        public ArticleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            item = itemView.findViewById(R.id.articles_cv);
            image = itemView.findViewById(R.id.articles_iv);
            title = itemView.findViewById(R.id.articles_title_tv);
            subTitle = itemView.findViewById(R.id.articles_subTitle_tv);

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClickArticle(position);
                        }
                    }
                }
            });

        }
    }
}
