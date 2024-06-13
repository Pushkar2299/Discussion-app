package com.discussion.api.service;

import com.discussion.api.exception.CustomException;
import com.discussion.api.model.Reply;
import com.discussion.api.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    public void createReply(Reply reply) {
        replyRepository.save(reply);
    }

    public void updateReply(Reply reply) {
        if(replyRepository.existsById(reply.getId()))
            replyRepository.save(reply);
        else throw new CustomException("Reply doesn't exist", HttpStatus.BAD_REQUEST);
    }

    public void deletereply(Long id) {
        if(replyRepository.existsById(id))
            replyRepository.deleteById(id);
        else throw new CustomException("Reply doesn't exist", HttpStatus.BAD_REQUEST);
    }

    public Reply getReplyList(Long id) {
       return replyRepository.findById(id).get();
    }
}
