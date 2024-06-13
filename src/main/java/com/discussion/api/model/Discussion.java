package com.discussion.api.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "discussion")
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private String picture;
    private Long createdBy;

    @CreatedDate
    private Date createdAt;

    @Transient
    List<Hashtag> hashtags =new ArrayList<>();

    @Transient
    List<Comment> comment = new ArrayList<>();
}
