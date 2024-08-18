package com.ictedu.springapp.model;

import lombok.Data;

import java.util.Date;

@Data
public class PostDTO {
    private Long postId;
    private String userId;
    private String location;
    private String content;
    private String hashtag;
    private String imgUrl;
    private Date createdAt;
    private Long likesCount;
    private Long commentsCount;
}
