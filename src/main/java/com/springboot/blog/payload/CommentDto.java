package com.springboot.blog.payload;

import lombok.Data;

@Data //generates getters setters hashcode, toString etc.

public class CommentDto {
    private long id;
    private String name;
    private String email;
    private String body;
}
