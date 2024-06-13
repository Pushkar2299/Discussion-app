package com.discussion.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "hashtag")
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hashtag;


    private Long discussionId;
}
