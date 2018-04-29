package com.se34.coursemap.service.impl;

import com.se34.coursemap.entity.Comment;
import com.se34.coursemap.repository.CommentRepository;
import com.se34.coursemap.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void addComment(Comment comment) {
        commentRepository.saveAndFlush(comment);
    }

    @Override
    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }


    @Override
    public List<Comment> getAllComments() {
        return null;
    }

    @Override
    public Comment getComment(int id) {
        return null;
    }
}
