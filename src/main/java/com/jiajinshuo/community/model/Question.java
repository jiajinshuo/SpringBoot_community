package com.jiajinshuo.community.model;

import lombok.Data;

/**
 * @author jiajinshuo
 * @create 2020-04-06 12:24
 */
@Data
public class Question {

    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;

}
