package com.springboot.ecommerceproject.service.impl;

import com.springboot.ecommerceproject.entity.Comment;
import com.springboot.ecommerceproject.entity.Product;
import com.springboot.ecommerceproject.exception.ECommerceAPIException;
import com.springboot.ecommerceproject.exception.ResourceNotFoundException;
import com.springboot.ecommerceproject.payload.CommentDto;
import com.springboot.ecommerceproject.repository.CommentRepository;
import com.springboot.ecommerceproject.repository.ProductRepository;
import com.springboot.ecommerceproject.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {


    private CommentRepository commentRepository;
    private ProductRepository productRepository;

    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, ProductRepository productRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDto createComment(long productId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        //retrieve post entity by id
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", productId));
        //set post to comment entity
        comment.setProduct(product);
        //save comment entity to db
        Comment newComment = commentRepository.save(comment);
        return mapToDTO(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByproductId(long productId) {
        //retrieve comments by productId
        List<Comment> comments = commentRepository.findByproductId(productId);
        //comment list of comment entities to list of comment dto's
        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long productId, long commentId) {
        //retrieve post entity by id
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", productId));
        //retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getProduct().getId().equals(product.getId())){
            throw new ECommerceAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        return mapToDTO(comment);
    }

    @Override
    public CommentDto updateComment(long productId, long commentId, CommentDto commentRequest) {
        //retrieve post entity by id
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", productId));
        //retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getProduct().getId().equals(product.getId())){
            throw new ECommerceAPIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
        }

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comment updatedComment =commentRepository.save(comment);
        return mapToDTO(updatedComment);
    }

    @Override
    public void deleteComment(long productId, long commentId) {
        //retrieve post entity by id
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", productId));
        //retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getProduct().getId().equals(product.getId())){
            throw new ECommerceAPIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
        }

        commentRepository.delete(comment);
    }

    private CommentDto mapToDTO(Comment comment){
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);
//        Comment comment = new Comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        return comment;
    }
}