package com.neuedu.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.neuedu.dao.UserInfoMapper;
import com.neuedu.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserInfoMapper userInfoMapper;
    @RequestMapping(value = "/login.do")
    @ResponseBody
    public UserInfo login(){
        /*return userInfoMapper.selectByPrimaryKey(id:);*/
        UserInfo userlogin=new UserInfo();
        userlogin.setId(1);
        userlogin.setUsername("zhangmin");
        return userlogin;
    }


}
