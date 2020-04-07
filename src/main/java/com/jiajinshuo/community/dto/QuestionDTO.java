package com.jiajinshuo.community.dto;

import com.jiajinshuo.community.model.User;
import lombok.Data;

/**
 * @author jiajinshuo
 * @create 2020-04-06 16:52
 */
@Data
public class QuestionDTO {
    //为了不破坏Question类与数据库中的对应关系
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
    private User user;
}
