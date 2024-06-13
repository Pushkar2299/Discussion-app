package com.discussion.api.service;

import com.discussion.api.exception.CustomException;
import com.discussion.api.model.Comment;
import com.discussion.api.repository.CommentRepository;
import com.discussion.api.repository.DiscussionRepository;
import com.discussion.api.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private ReplyRepository replyRepository;

    public void createComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void updateComment(Comment comment) {
        if(commentRepository.existsById(comment.getId()))
            commentRepository.save(comment);
        else throw new CustomException("Comment doesn't exist", HttpStatus.BAD_REQUEST);
    }

    public List<Comment> commentList(Long id) {

        List<Comment> commentList = new ArrayList<>();
        if(discussionRepository.existsById(id)) {
            commentList = commentRepository.findAllByDiscussionId(id);
            for(Comment comment : commentList){
                comment.setReplyList(replyRepository.findAllByCommentId(comment.getId()));
            }
        }
        else throw new CustomException("Discussion doesn't exist", HttpStatus.BAD_REQUEST);

        return commentList;
    }

    public void deleteComment(Long id) {
        if(commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            replyRepository.deleteAllByCommentId(id);
        }
        else throw new CustomException("Comment doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
