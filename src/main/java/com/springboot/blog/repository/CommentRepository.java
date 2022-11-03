package com.springboot.blog.repository;

import com.springboot.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository we can remove this annotation
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
