package com.discussion.api.controller;

import com.discussion.api.exception.CustomException;
import com.discussion.api.model.Discussion;
import com.discussion.api.service.DiscussionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/discussion")
public class DiscussionController {

    @Autowired
    private DiscussionService discussionService;

    @PostMapping("/createDiscussion")
    public ResponseEntity<?> createDiscussion(@RequestBody Discussion discussion) {
        discussionService.createDiscussion(discussion);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Discussion Created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDiscussion(@RequestBody Discussion discussion) {

        discussionService.updateDiscussion(discussion);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "discussion Updated");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUSer(@PathVariable Long id) {

        if (null == id)
            throw new CustomException("ID should not be blank or null", HttpStatus.BAD_REQUEST);

        discussionService.deleteDiscussion(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Discussion Deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/discussionListByUser/{userId}")
    public ResponseEntity<?> discussionList(@PathVariable Long userId) {

        List<Discussion> discussionList = discussionService.getDiscussionList(userId);

        Map<String, Object> response = new HashMap<>();
        response.put("Discussion List", discussionList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/discussionListByText")
    public ResponseEntity<?> searchUser(@RequestHeader(value = "text") String text) {

        if (StringUtils.isEmpty(text))
            throw new CustomException("text should not be blank or null", HttpStatus.BAD_REQUEST);

          List<Discussion> discussionList = discussionService.searchDiscussionByText(text);

        Map<String, Object> response = new HashMap<>();
        response.put("Discussion List", discussionList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
