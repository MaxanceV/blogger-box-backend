package com.dauphine.bloggerboxbackend.controllers;

import com.dauphine.bloggerboxbackend.dto.PostRequest;
import com.dauphine.bloggerboxbackend.models.Post;
import com.dauphine.bloggerboxbackend.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@Tag(
        name = "Post API",
        description = "Post endpoints"
)
@RequestMapping("/v1/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "Get all posts", description = "Retrieve all posts or search by partial value in title/content")
    @GetMapping
    public ResponseEntity<List<Post>> getAll(@RequestParam(required = false) String value) {
        List<Post> posts;
        if (value == null) {
            posts = postService.getAll();
        } else {
            posts = postService.getAllByValue(value);
        }
        return ResponseEntity.ok(posts);
    }

    @Operation(summary = "Get a post by ID", description = "Retrieve a post by its UUID, or throw 404 if not found")
    @GetMapping("{id}")
    public ResponseEntity<Post> getById(@PathVariable UUID id) {
        Post post = postService.getById(id);
        return ResponseEntity.ok(post);
    }

    @Operation(summary = "Create a new post", description = "Create a post from the provided PostRequest (title, content, category)")
    @PostMapping
    public ResponseEntity<Post> create(@RequestBody PostRequest postRequest) {
        Post post = postService.create(postRequest);
        return ResponseEntity
                .created(URI.create("/v1/posts/" + post.getUuid()))
                .body(post);
    }

    @Operation(summary = "Update a post", description = "Update the fields of an existing post (title, content, category) by ID")
    @PutMapping("{id}")
    public ResponseEntity<Post> update(
            @PathVariable UUID id,
            @RequestBody PostRequest postRequest
    ) {
        Post updated = postService.update(id, postRequest.getTitle(), postRequest.getTitle(), postRequest.getCategory_id());
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete a post by ID", description = "Delete the specified post, returning 204 if successful")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        boolean deleted = postService.deletePost(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get latest posts", description = "Retrieve all posts sorted by creation date in descending order")
    @GetMapping("/latest")
    public ResponseEntity<List<Post>> getAllSortedByCreationDate() {
        List<Post> posts = postService.getAllSortedByCreationDateDesc();
        return ResponseEntity.ok(posts);
    }

    @Operation(summary = "Get posts by category", description = "Retrieve all posts belonging to a specific category by category ID")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Post>> getAllByCategory(@PathVariable UUID categoryId) {
        List<Post> posts = postService.getAllByCategory(categoryId);
        return ResponseEntity.ok(posts);
    }
}
