package com.discussion.api.controller;

import com.discussion.api.model.Comment;
import com.discussion.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/createComment")
    public ResponseEntity<?> createComment(@RequestBody Comment comment) {
        commentService.createComment(comment);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "comment Created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/updateComment")
    public ResponseEntity<?> updateComment(@RequestBody Comment comment) {
        commentService.updateComment(comment);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "comment Updated");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteComment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "id") Long id) {
        commentService.deleteComment(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "comment deleted");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getComment/{id}")
    public ResponseEntity<?> commentList(@PathVariable(value = "id") Long id) {
        List<Comment> commentList = commentService.commentList(id);
        Map<String, Object> response = new HashMap<>();
        response.put("Comments", commentList);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
