package com.se34.coursemap.service.impl;

import com.se34.coursemap.entity.Comment;
import com.se34.coursemap.service.CommentService;
import com.se34.coursemap.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment add(Comment comment) {
        Comment savedComment = commentRepository.saveAndFlush(comment);

        return savedComment;
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public Comment edit(Comment comment) {
        return commentRepository.saveAndFlush(comment);
    }
}
