package com.se34.coursemap.repository;

import com.se34.coursemap.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

interface CommentRepository extends JpaRepository<Comment, Integer>{
}