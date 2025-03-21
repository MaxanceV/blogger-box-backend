package com.dauphine.bloggerboxbackend.models;

import java.util.Date;
import java.util.UUID;

public class Post {
    private UUID uuid;
    private String title;
    private String content;
    private Date created_date;
    private Category category;

    public Post(UUID uuid, String title, String content, Date created_date, Category category) {
        this.uuid = uuid;
        this.title = title;
        this.content = content;
        this.created_date = created_date;
        this.category = category;
    }

    public Post() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
