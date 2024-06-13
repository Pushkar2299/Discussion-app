package com.discussion.api.repository;

import com.discussion.api.model.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion,Long> {

    List<Discussion> findAllByCreatedBy(Long userId);

    @Query(value = "select * from discussion where text like (%?1%)",nativeQuery = true)
    List<Discussion> getAllDiscussionByText(String text);
}
