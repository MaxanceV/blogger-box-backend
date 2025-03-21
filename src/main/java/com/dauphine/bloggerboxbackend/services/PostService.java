package com.dauphine.bloggerboxbackend.services;

import com.dauphine.bloggerboxbackend.dto.CategoryRequest;
import com.dauphine.bloggerboxbackend.dto.PostRequest;
import com.dauphine.bloggerboxbackend.models.Category;
import com.dauphine.bloggerboxbackend.models.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final List<Post> posts;
    private final CategoryService categoryService;

    public PostService(CategoryService categoryService) {
        this.posts = new ArrayList<Post>();
        this.categoryService = categoryService;
    }

    public List<Post> getAll() {
        return this.posts;
    }

    public Post getById(UUID id) {
        for (Post post : this.posts) {
            if(post.getUuid().equals(id)){
                return post;
            }
        }
        return null;
    }

    public Post create(PostRequest postRequest){
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setCreated_date(postRequest.getCreated_date());
        post.setCategory(categoryService.getById(postRequest.getCategory_id()));
        post.setUuid(UUID.randomUUID());
        posts.add(post);
        return post;
    }

    public Post update(UUID id, String title, String content, UUID category_id){
        Post ret = getById(id);
        ret.setTitle(title);
        ret.setContent(content);
        ret.setCategory(categoryService.getById(category_id));
        return ret;
    }

    public UUID deletePost(UUID id){
        if(!posts.contains(getById(id))){
            return null;
        }
        posts.remove(getById(id));
        return id;
    }

    public List<Post> getAllSortedByCreationDateDesc() {
        return this.posts.stream()
                .sorted(Comparator.comparing(Post::getCreated_date).reversed())
                .collect(Collectors.toList());
    }

    public List<Post> getAllByCategory(UUID categoryId) {
        return this.posts.stream()
                .filter(post -> post.getCategory() != null && post.getCategory().getUuid().equals(categoryId))
                .collect(Collectors.toList());
    }

}
