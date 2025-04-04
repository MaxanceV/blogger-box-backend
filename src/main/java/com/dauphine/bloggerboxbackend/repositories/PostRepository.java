package com.dauphine.bloggerboxbackend.repositories;

import com.dauphine.bloggerboxbackend.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    @Query("""
           SELECT post
           FROM Post post
           WHERE UPPER(post.title) LIKE UPPER(CONCAT('%', :value , '%')) OR UPPER(post.content) LIKE UPPER(CONCAT('%', :value , '%'))
           """)
    List<Post> findAllByValue(@Param("value") String value);
}
