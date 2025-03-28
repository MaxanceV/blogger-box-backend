package com.dauphine.bloggerboxbackend.models;

import java.util.UUID;

public class Category {
    private UUID uuid;
    private String name;

    public Category(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public Category() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
