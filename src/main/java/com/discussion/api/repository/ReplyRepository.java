package com.discussion.api.repository;

import com.discussion.api.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    void deleteAllByCommentId(Long id);

    List<Reply> findAllByCommentId(Long id);
}
