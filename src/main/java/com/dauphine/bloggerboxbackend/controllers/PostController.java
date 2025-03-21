package com.dauphine.bloggerboxbackend.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(
        name="Post API",
        description = "Post endpoints"
)
@RequestMapping("/v1/posts")
public class PostController {
}
