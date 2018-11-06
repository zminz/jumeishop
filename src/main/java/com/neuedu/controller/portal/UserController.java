package com.neuedu.controller.portal;


import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value ="/user")
public class UserController {

    @Autowired
    IUserService userService;
    /**
     * 登录
     */
    @RequestMapping(value ="/login.do")
    public ServerResponse login(
            HttpSession session,
            /*@RequestParam(value = "username")*/String username,
            /*@RequestParam("password")*/String password){

        ServerResponse serverResponse=userService.login(username,password);
        if(serverResponse.isSuccess()){//登录成功
            UserInfo userInfo=(UserInfo) serverResponse.getData();

            session.setAttribute(Const.CURRENRUSER,userInfo);
        }
        return serverResponse;
    }

    /**
     * 注册
     */
    @RequestMapping(value ="/register.do")
    public ServerResponse register(HttpSession session,UserInfo userInfo){
       ServerResponse serverResponse=userService.register(userInfo);

        return serverResponse;
    }

    /**
     * 根据用户名查询密保问题
     */
    @RequestMapping(value ="/forget_get_question.do")
    public ServerResponse forget_get_question(String username){
        ServerResponse serverResponse=userService.forget_get_question(username);

        return serverResponse;
    }

    /**
     * 提交问题答案
     */
    @RequestMapping(value ="/forget_check_answer.do")
    public ServerResponse forget_check_answer(String username,String question,String answer){
        ServerResponse serverResponse=userService.forget_check_answer(username,question,answer);

        return serverResponse;
    }

    /**
     * 忘记密码的重置密码
     */
    @RequestMapping(value ="/forget_reset_password.do")
    public ServerResponse forget_reset_password(String username,String passwordNew,String forgetToken){
        ServerResponse serverResponse=userService.forget_reset_password(username,passwordNew,forgetToken);

        return serverResponse;
    }

    /**
     * 检查用户名或者邮箱是否有效
     */
    @RequestMapping(value ="/check_valid.do")
    public ServerResponse check_valid(String str,String type){
        ServerResponse serverResponse=userService.check_valid(str,type);

        return serverResponse;
    }


    /**
     * 获取登录用户信息
     */
    @RequestMapping(value ="/get_user_info.do")
    public ServerResponse get_user_info(HttpSession session){
        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENRUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("用户未登录");
        }
       /* userInfo.setPassword("");
        userInfo.setQuestion("");
        userInfo.setAnswer("");
        userInfo.setRole(null);
        return ServerResponse.serverResponseBySuccess(userInfo);*/
       UserInfo newUserInfo=new UserInfo();
       newUserInfo.setId(userInfo.getId());
       newUserInfo.setUsername(userInfo.getUsername());
       newUserInfo.setEmail(userInfo.getEmail());
       newUserInfo.setPhone(userInfo.getPhone());
       newUserInfo.setCreateTime(userInfo.getCreateTime());
       newUserInfo.setUpdateTime(userInfo.getUpdateTime());

       return ServerResponse.serverResponseBySuccess(newUserInfo);

    }


    /**
     * 登录状态重置密码
     */
    @RequestMapping(value ="/reset_password.do")
    public ServerResponse reset_password(HttpSession session,String passwordOld,String passwordNew){
        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENRUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("用户未登录");
        }
        return userService.reset_password(userInfo.getUsername(),passwordOld,passwordNew);
    }


    /**
     * 登录状态更新个人信息
     */
    @RequestMapping(value ="/update_information.do")
    public ServerResponse update_information(HttpSession session,UserInfo user){
        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENRUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("用户未登录");
        }
        user.setId(userInfo.getId());
        ServerResponse serverResponse= userService.update_information(user);

        if(serverResponse.isSuccess()){
            //更新session中用户信息
            UserInfo userInfo1=userService.findUserInfoByUserid(userInfo.getId());
            session.setAttribute(Const.CURRENRUSER,userInfo1);
        }
        return serverResponse;
    }

    /**
     * 获取登录用户详细信息
     */
    @RequestMapping(value ="/get_information.do")
    public ServerResponse get_information(HttpSession session){
        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENRUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("用户未登录");
        }

        userInfo.setPassword("");

        return ServerResponse.serverResponseBySuccess(userInfo);
    }

    /**
     * 退出登录
     */
    @RequestMapping(value ="/logout.do")
    public ServerResponse logout(HttpSession session){
        session.removeAttribute(Const.CURRENRUSER);
        return ServerResponse.serverResponseBySuccess();
    }



}
