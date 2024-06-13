package com.discussion.api.controller;

import com.discussion.api.exception.CustomException;
import com.discussion.api.model.User;
import com.discussion.api.service.UserService;
import com.discussion.api.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        userService.signup(user);
        response.put("message", "User Created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        userService.update(user);
        response.put("message", "User Updated");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUSer(@PathVariable Long id) {

        if (null == id)
            throw new CustomException("ID should not be blank or null", HttpStatus.BAD_REQUEST);

        userService.deleteUser(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User Deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/userList")
    public ResponseEntity<?> userList() {

        List<User> userList = userService.getUserList();

        Map<String, Object> response = new HashMap<>();
        response.put("User List", userList);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/searchUser")
    public ResponseEntity<?> searchUser(@RequestHeader(value = "name") String name) {

        if (StringUtils.isEmpty(name)) {
            throw new CustomException("Name should not be blank or null", HttpStatus.BAD_REQUEST);
        }

        List<UserVO> userList = userService.getSearchUser(name);

        Map<String, Object> response = new HashMap<>();
        response.put("User List", userList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
