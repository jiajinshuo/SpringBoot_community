package com.jiajinshuo.community.model;

import lombok.Data;

/**
 * @author jiajinshuo
 * @create 2020-04-04 19:02
 */
@Data
public class User {

    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;

}
