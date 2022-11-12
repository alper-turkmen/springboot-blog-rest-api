package com.springboot.ecommerceproject.controller;
import com.springboot.ecommerceproject.payload.CommentDto;
import com.springboot.ecommerceproject.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping("/products/{productId}/comments")
    public ResponseEntity<CommentDto> createComment(

            @PathVariable(value = "productId") long productId, @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(productId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/products/{productId}/comments")
    public List<CommentDto> getCommentsByproductId(@PathVariable(value = "productId") long productId){
        System.out.println(productId);
        return commentService.getCommentsByproductId(productId);
    }

    @GetMapping("/products/{productId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "productId") long productId, @PathVariable(value = "id") long commentId){
        CommentDto commentDto = commentService.getCommentById(productId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @PutMapping("/products/{productId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment( @PathVariable(value = "productId") long productId,
                                                     @PathVariable(value = "id") long commentId,
                                                     @Valid @RequestBody CommentDto commentDto){
        CommentDto updatedComment = commentService.updateComment(productId, commentId, commentDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/products/{productId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "productId") long productId,
                                                @PathVariable(value = "id") long commentId){
        //we can ignore alias if alias is same with variable name

        commentService.deleteComment(productId, commentId);
        return new ResponseEntity<>("Comment deleted succesfully", HttpStatus.OK);
    }
}