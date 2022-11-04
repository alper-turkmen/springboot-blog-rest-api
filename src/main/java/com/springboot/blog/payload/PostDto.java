package com.springboot.blog.payload;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data //Lombok generates getter and setters
public class PostDto {
    private long id;

    //title not should be empty or null
    //title should have at least 2 character
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least two characters")
    private String title;

    //description not should be empty or null
    //description should have at least 2 character
    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;


    //description not should be empty or null
    //description should have at least 2 character
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
}