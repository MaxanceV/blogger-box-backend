package com.dauphine.bloggerboxbackend.dto;

import com.dauphine.bloggerboxbackend.models.Category;

import java.util.Date;
import java.util.UUID;

//DTO data transfert object
public class PostRequest {
    private String title;
    private String content;
    private Date created_date;
    private UUID category_id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public UUID getCategory_id() {
        return category_id;
    }

    public void setCategory_id(UUID category_id) {
        this.category_id = category_id;
    }
}
