package com.discussion.api.repository;

import com.discussion.api.model.User;
import com.discussion.api.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserVORepository extends JpaRepository<UserVO,Long> {

    @Query(value = "select u.id, u.name, u.mobile_number, u.email from user u", nativeQuery = true)
    List<UserVO> searchUserByName(String name);
}
