package com.dauphine.bloggerboxbackend.services;

import com.dauphine.bloggerboxbackend.dto.PostRequest;
import com.dauphine.bloggerboxbackend.models.Post;
import com.dauphine.bloggerboxbackend.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl {

    private final CategoryService categoryService;
    private final PostRepository repository;

    public PostServiceImpl(PostRepository repository, CategoryService categoryService) {
        this.categoryService = categoryService;
        this.repository = repository;
    }

    public List<Post> getAll() {
        return repository.findAll();
    }

    public Post getById(UUID id) {
        return repository.findById(id)
                .orElse(null);
    }

    public Post create(PostRequest postRequest){
        Post post = new Post(postRequest.getTitle(),postRequest.getContent(),categoryService.getById(postRequest.getCategory_id()));
        return repository.save(post);
    }

    public Post update(UUID id, String title, String content, UUID category_id){
        Post post = getById(id);
        if(post == null){
            return null;
        }
        post.setTitle(title);
        post.setContent(content);
        post.setCategory(categoryService.getById(category_id));
        return repository.save(post);
    }

    public boolean deletePost(UUID id){
        repository.deleteById(id);
        return true;
    }

    public List<Post> getAllSortedByCreationDateDesc() {
        return repository.findAll()
                .stream()
                .sorted(Comparator.comparing(Post::getCreatedDate).reversed())
                .collect(Collectors.toList());
    }

    public List<Post> getAllByCategory(UUID categoryId) {
        return repository.findAll()
                .stream()
                .filter(post -> post.getCategory() != null && post.getCategory().getUuid().equals(categoryId))
                .collect(Collectors.toList());
    }

}
