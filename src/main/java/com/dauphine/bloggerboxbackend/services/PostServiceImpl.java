package com.dauphine.bloggerboxbackend.services;

import com.dauphine.bloggerboxbackend.dto.PostRequest;
import com.dauphine.bloggerboxbackend.exception.PostAlreadyExistException;
import com.dauphine.bloggerboxbackend.exception.PostNotFoundByIdException;
import com.dauphine.bloggerboxbackend.models.Post;
import com.dauphine.bloggerboxbackend.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final CategoryService categoryService;
    private final PostRepository repository;

    public PostServiceImpl(PostRepository repository, CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Post> getAll() {
        return repository.findAll();
    }

    @Override
    public Post getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new PostNotFoundByIdException(id));
    }

    @Override
    public Post create(PostRequest postRequest) {
        if (repository.existsByTitle(postRequest.getTitle())) {
            throw new PostAlreadyExistException(postRequest.getTitle());
        }

        Post post = new Post(
                postRequest.getTitle(),
                postRequest.getContent(),
                categoryService.getById(postRequest.getCategory_id())
        );
        return repository.save(post);
    }

    @Override
    public Post update(UUID id, String title, String content, UUID category_id) {
        Post post = getById(id);

        if (!post.getTitle().equalsIgnoreCase(title) && repository.existsByTitle(title)) {
            throw new PostAlreadyExistException(title);
        }

        post.setTitle(title);
        post.setContent(content);
        post.setCategory(categoryService.getById(category_id));

        return repository.save(post);
    }

    @Override
    public boolean deletePost(UUID id) {
        getById(id);
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Post> getAllSortedByCreationDateDesc() {
        return repository.findAll()
                .stream()
                .sorted(Comparator.comparing(Post::getCreatedDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> getAllByCategory(UUID categoryId) {
        return repository.findAll()
                .stream()
                .filter(post -> post.getCategory() != null
                        && post.getCategory().getUuid().equals(categoryId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> getAllByValue(String value) {
        return repository.findAllByValue(value);
    }
}
