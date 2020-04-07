package com.jiajinshuo.community.service;

import com.jiajinshuo.community.dto.PaginationDTO;
import com.jiajinshuo.community.dto.QuestionDTO;
import com.jiajinshuo.community.mapper.QuestionMapper;
import com.jiajinshuo.community.mapper.UserMapper;
import com.jiajinshuo.community.model.Question;
import com.jiajinshuo.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiajinshuo
 * @create 2020-04-06 17:03
 */
@Service
public class QuestionService {
    @Autowired(required = false)
    QuestionMapper questionMapper;
    
    @Autowired(required = false)
    UserMapper userMapper;
    public PaginationDTO list(Integer page, Integer size) {

        //size * (page - 1)
        Integer offset = size * (page -1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
//            把一个对象的所有属性拷贝到另一个上面
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);

            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        //获得总的记录数
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount,page,size);
        return paginationDTO;
    }
}
