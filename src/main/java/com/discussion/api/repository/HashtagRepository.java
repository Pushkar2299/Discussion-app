package com.discussion.api.repository;

import com.discussion.api.model.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag,Long> {
    List<Hashtag> findAllByDiscussionIdIn(Set<Long> discussionIdList);

    Boolean existsByDiscussionId(Long id);
}
