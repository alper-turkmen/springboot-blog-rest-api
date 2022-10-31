package com.springboot.blog.payload;
import lombok.Data;

@Data //Lombok generates getter and setters
public class PostDto {
    private long id;
    private String title;
    private String description;
    private String content;
}