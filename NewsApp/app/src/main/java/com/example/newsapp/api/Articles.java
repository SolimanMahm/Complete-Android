package com.example.newsapp.api;

public class Articles {
    private Source source;
    private String author, title, description, url, urlToImage, publishedAt, content;

    public Articles(String description, String url, String urlToImage, String content, String title) {
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.content = content;
        this.title = title;
    }

    public Source getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }
}
