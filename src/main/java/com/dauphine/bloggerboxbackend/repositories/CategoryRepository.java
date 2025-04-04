package com.dauphine.bloggerboxbackend.repositories;

import com.dauphine.bloggerboxbackend.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query("""
           SELECT category
           FROM Category category
           WHERE UPPER(category.name) LIKE UPPER(CONCAT('%', :name , '%'))
           """)
    List<Category> findAllByName(@Param("name") String name);

    @Query("""
           SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END
           FROM Category c
           WHERE UPPER(c.name) = UPPER(:name)
           """)
    boolean existsByName(@Param("name") String name);
}
