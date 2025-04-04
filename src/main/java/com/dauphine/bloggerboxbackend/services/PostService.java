package com.dauphine.bloggerboxbackend.services;

import com.dauphine.bloggerboxbackend.dto.PostRequest;
import com.dauphine.bloggerboxbackend.models.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public interface PostService {

    public List<Post> getAll();

    public Post getById(UUID id) ;

    public Post create(PostRequest postRequest);

    public Post update(UUID id, String title, String content, UUID category_id);

    public boolean deletePost(UUID id);

    public List<Post> getAllSortedByCreationDateDesc();

    public List<Post> getAllByCategory(UUID categoryId);

}
