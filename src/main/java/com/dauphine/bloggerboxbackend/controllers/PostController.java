package com.dauphine.bloggerboxbackend.controllers;

import com.dauphine.bloggerboxbackend.dto.CategoryRequest;
import com.dauphine.bloggerboxbackend.dto.PostRequest;
import com.dauphine.bloggerboxbackend.models.Category;
import com.dauphine.bloggerboxbackend.models.Post;
import com.dauphine.bloggerboxbackend.services.CategoryService;
import com.dauphine.bloggerboxbackend.services.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(
        name="Post API",
        description = "Post endpoints"
)
@RequestMapping("/v1/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAll(){
        return postService.getAll();
    }

    @GetMapping("{id}")
    public Post getById(@PathVariable UUID id){
        return postService.getById(id);
    }

    @PostMapping
    public Post create(PostRequest postRequest){
        return postService.create(postRequest);
    }

    @PutMapping("{id}")
    public Post update(@PathVariable UUID id, @RequestBody String title, @RequestBody String content, @RequestBody UUID category_id){
        return postService.update(id, title, content, category_id);
    }

    @DeleteMapping("{id}")
    public UUID deleteCategory(@PathVariable UUID id){
        return postService.deletePost(id);
    }

    @GetMapping("/latest")
    public List<Post> getAllSortedByCreationDate() {
        return postService.getAllSortedByCreationDateDesc();
    }

    @GetMapping("/category/{categoryId}")
    public List<Post> getAllByCategory(@PathVariable UUID categoryId) {
        return postService.getAllByCategory(categoryId);
    }

}
