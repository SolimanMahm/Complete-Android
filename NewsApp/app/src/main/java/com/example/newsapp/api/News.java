package com.example.newsapp.api;

import java.util.List;

public class News {
    private String status;
    private int totalResults;

    private List<Articles> articles;

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Articles> getArticles() {
        return articles;
    }
}
