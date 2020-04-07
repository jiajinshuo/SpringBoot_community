package com.jiajinshuo.community.dto;

import lombok.Data;

/**
 * @author jiajinshuo
 * @create 2020-04-04 13:33
 */
@Data
public class AccessTokenDTO {

    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;


}
