package com.jiajinshuo.community.controller;

import com.jiajinshuo.community.mapper.QuestionMapper;
import com.jiajinshuo.community.model.Question;
import com.jiajinshuo.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author jiajinshuo
 * @create 2020-04-06 10:29
 */
@Controller
public class PublishController {

    @Autowired(required = false)
    private QuestionMapper questionMapper;

    @GetMapping(value = "/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping(value = "/publish")
    public String doPublish(@RequestParam(value = "title") String title,
                            @RequestParam(value = "description") String description,
                            @RequestParam(value = "tag") String tag,
                            HttpServletRequest request,
                            Model model,
                            HttpSession session){

        User user = (User)session.getAttribute("user");

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTitle(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        questionMapper.create(question);
        return "index";

    }
}
