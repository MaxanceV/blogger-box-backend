package com.dauphine.bloggerboxbackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @Column(name = "id")
    private UUID uuid;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "created_date")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Post(String title,
                String content,
                Category category) {
        this.uuid = UUID.randomUUID();
        this.title = title;
        this.content = content;
        this.createdDate = LocalDateTime.now();
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime created_date) {
        this.createdDate = created_date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
