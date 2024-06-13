package com.discussion.api.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserVO {

    @Id
    private Long id;
    private String name;
    private String mobileNumber;
    private String email;
}
