package com.jiajinshuo.community.controller;

import com.jiajinshuo.community.dto.AccessTokenDTO;
import com.jiajinshuo.community.dto.GithubUser;
import com.jiajinshuo.community.mapper.UserMapper;
import com.jiajinshuo.community.model.User;
import com.jiajinshuo.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author jiajinshuo
 * @create 2020-04-04 13:12
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.direct.uri}")
    private String directUri;

    @Autowired(required = false)
    UserMapper userMapper;
    @GetMapping(value = "/callback")
    public String callback(@RequestParam(value = "code" ) String code,
                           @RequestParam(value = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(directUri);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
//        System.out.println(user.getName());
        if(githubUser != null){
            System.out.println("1111");
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());

            userMapper.insert(user);
            //用数据库实物的存储代替session的写入
            response.addCookie(new Cookie("token",token));
//            request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        }else {
            System.out.println("登录失败");
            //登录失败
            return "index";
        }
    }
}
