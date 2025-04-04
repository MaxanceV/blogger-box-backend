package com.dauphine.bloggerboxbackend.repositories;

import com.dauphine.bloggerboxbackend.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
