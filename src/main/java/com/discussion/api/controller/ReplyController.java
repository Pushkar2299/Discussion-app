package com.discussion.api.controller;

import com.discussion.api.model.Reply;
import com.discussion.api.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import java.util.Map;

@RestController
@RequestMapping("/api/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping("/createReply")
    public ResponseEntity<?> createReply(@RequestBody Reply reply) {
        replyService.createReply(reply);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "reply Created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/updateReply")
    public ResponseEntity<?> updateReply(@RequestBody Reply reply) {
        replyService.updateReply(reply);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "reply Updated");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteReply/{id}")
    public ResponseEntity<?> deleteReply(@PathVariable(value = "id") Long id) {
        replyService.deletereply(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "reply deleted");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getReply/{id}")
    public ResponseEntity<?> replyList(@PathVariable(value = "id") Long id) {
        Reply reply = replyService.getReplyList(id);
        Map<String, Object> response = new HashMap<>();
        response.put("reply", reply);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
