package com.se34.coursemap.controller;

import com.se34.coursemap.entity.Comment;
import com.se34.coursemap.service.CommentService;
import com.se34.coursemap.utill.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {


    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> institutes = commentService.getAllComments();
        if (institutes.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(institutes, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Comment> getComment(@PathVariable("id") int id) {
        Comment comment = commentService.getComment(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> createComment(@RequestBody Comment comment, UriComponentsBuilder ucBuilder) {

        commentService.addComment(comment);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/comment/{id}").buildAndExpand(comment.getCommentId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Comment> deleteComment(@PathVariable("id") int id) {

        Comment comment = commentService.getComment(id);
        if (comment == null) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. Comment with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
