package com.jiajinshuo.community.dto;

import lombok.Data;

/**
 * @author jiajinshuo
 * @create 2020-04-04 14:11
 */
@Data
public class GithubUser {

    private String name;
    private Long id;
    private String bio;
    private String avatar_url;

}
