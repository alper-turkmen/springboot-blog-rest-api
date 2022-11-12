package com.springboot.ecommerceproject.payload;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data //Lombok generates getter and setters
public class ProductDto {
    private long id;

    //title not should be empty or null
    //title should have at least 2 character
    @NotEmpty
    @Size(min = 2, message = "Product title should have at least two characters")
    private String title;

    //description not should be empty or null
    //description should have at least 2 character
    @NotEmpty
    @Size(min = 10, message = "Product description should have at least 10 characters")
    private String description;


    //description not should be empty or null
    //description should have at least 2 character
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
}