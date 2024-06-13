package com.discussion.api.service;

import com.discussion.api.exception.CustomException;
import com.discussion.api.model.User;
import com.discussion.api.repository.UserRepository;
import com.discussion.api.repository.UserVORepository;
import com.discussion.api.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserVORepository userVORepository;

    public void signup(User user) {
        userRepository.save(user);
    }

    public void update(User user) {
        userRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException("user not found", HttpStatus.BAD_REQUEST)) ;

        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new CustomException("user not found", HttpStatus.BAD_REQUEST)) ;

        userRepository.deleteById(id);
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public List<UserVO> getSearchUser(String name) {
        return userVORepository.searchUserByName(name);
    }
}
