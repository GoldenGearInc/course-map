package com.se34.coursemap.service;

import com.se34.coursemap.entity.Comment;

public interface CommentService {

    Comment add(Comment comment);

    void delete(Comment comment);

    Comment edit(Comment comment);

}
