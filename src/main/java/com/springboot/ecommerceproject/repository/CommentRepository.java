package com.springboot.ecommerceproject.repository;
import com.springboot.ecommerceproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// @Repository we can remove this annotation
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByproductId(long productId);

}
