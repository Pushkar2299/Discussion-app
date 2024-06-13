package com.discussion.api.service;

import com.discussion.api.exception.CustomException;
import com.discussion.api.model.Discussion;
import com.discussion.api.model.Hashtag;
import com.discussion.api.repository.DiscussionRepository;
import com.discussion.api.repository.HashtagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DiscussionService {

    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private HashtagRepository hashtagRepository;


    public void createDiscussion(Discussion discussion) {
        discussionRepository.save(discussion);
        for (Hashtag hashtag : discussion.getHashtags()){
            hashtag.setDiscussionId(discussion.getId());
        }
        hashtagRepository.saveAll(discussion.getHashtags());
    }

    public void updateDiscussion(Discussion discussion) {
        if (discussionRepository.existsById(discussion.getId())) {
            discussionRepository.save(discussion);
            for (Hashtag hashtag : discussion.getHashtags()) {
                hashtag.setDiscussionId(discussion.getId());
            }
            hashtagRepository.saveAll(discussion.getHashtags());
        }
        else throw new CustomException("Discussion doesn't exist", HttpStatus.BAD_REQUEST);
    }

    public void deleteDiscussion(Long id) {

        discussionRepository.findById(id)
                .orElseThrow(() -> new CustomException("discussion not found", HttpStatus.BAD_REQUEST)) ;

        discussionRepository.deleteById(id);
        List<Hashtag>hastagList = hashtagRepository.findAllByDiscussionIdIn(Collections.singleton(id));
        hashtagRepository.deleteAllInBatch(hastagList);
    }

    public List<Discussion> getDiscussionList(Long userId) {

        List<Discussion> discussionList = discussionRepository.findAllByCreatedBy(userId);

        Set<Long> discussionIdList = discussionList.parallelStream().map(Discussion::getId).collect(Collectors.toSet());
        List<Hashtag> hashtagList = hashtagRepository.findAllByDiscussionIdIn(discussionIdList);
        Map<Long,List<Hashtag>> hashtagMap = hashtagList.stream().collect(Collectors.groupingBy(Hashtag::getDiscussionId));

        for (Discussion discussion : discussionList)
            discussion.setHashtags(hashtagMap.get(discussion.getId()));

        return  discussionList;
    }

    public List<Discussion> searchDiscussionByText(String text) {

        List<Discussion> discussionList = discussionRepository.getAllDiscussionByText(text);

        Set<Long> discussionIdList = discussionList.parallelStream().map(Discussion::getId).collect(Collectors.toSet());
        List<Hashtag> hashtagList = hashtagRepository.findAllByDiscussionIdIn(discussionIdList);
        Map<Long,List<Hashtag>> hashtagMap = hashtagList.stream().collect(Collectors.groupingBy(Hashtag::getDiscussionId));

        for (Discussion discussion : discussionList)
            discussion.setHashtags(hashtagMap.get(discussion.getId()));

        return  discussionList;
    }
}
