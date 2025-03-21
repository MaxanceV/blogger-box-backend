package com.dauphine.bloggerboxbackend.models;

import java.util.Date;
import java.util.UUID;

public class Post {
    private UUID uuid;
    private String title;
    private String content;
    private Date created_date;
    private Category category;
}
