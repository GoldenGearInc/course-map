package com.se34.coursemap.service;

import com.se34.coursemap.entity.Comment;

import java.util.List;

public interface CommentService {
    void addComment(Comment comment);

    void deleteComment(int id);

    List<Comment> getAllComments();

    Comment getComment(int id);

}
