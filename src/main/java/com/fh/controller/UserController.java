package com.fh.controller;

import com.fh.common.Ignore;
import com.fh.common.LogMsg;
import com.fh.common.ServerResponse;
import com.fh.model.User;
import com.fh.param.UserSearchParam;
import com.fh.service.user.UserService;
import com.fh.util.Md5Util;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user/")
public class UserController {
    @Resource
    private UserService userService;
    private User user;

    /*    @RequestMapping("checkUserByName")
        @ResponseBody
        public ServerResponse checkUserByName( String userName){

            return userService.getUserByName(userName);
        }
        @RequestMapping("queryList")
        @ResponseBody
        public ServerResponse queryList(){
            return userService.queryList();
        }*/
    @RequestMapping("index")
    public String index(){
        return "user/list";
    }
    @RequestMapping("checkUserByName")
    @ResponseBody
    @Ignore
    public Map checkUserByName( String userName){
         Map map=new HashMap();
        map.put("valid",userService.getUserByName(userName));
        return map;
    }
    @RequestMapping("queryList")
    @ResponseBody
    public ServerResponse queryList(UserSearchParam userSearchParam){

        return userService.queryList(userSearchParam);
    }

    @RequestMapping("addUser")
    @ResponseBody
    @Ignore
    public ServerResponse addUser(User user, @RequestParam("roleArr[]") List<Integer> roleArr){

        return userService.addUser(user,roleArr);
    }



    @RequestMapping("insertUser")
    @ResponseBody
    @Ignore
    @LogMsg("注册用户")
    public ServerResponse insertUser(User user) {

            String salt = RandomStringUtils.randomAlphabetic(20);
            String encodePwd = Md5Util.md5(Md5Util.md5(user.getPassWord() + salt));
            user.setPassWord(encodePwd);
            user.setSalt(salt);
            user.setJoinTime(new Date());
            userService.insertUser(user);

        return ServerResponse.success();

    }
        @RequestMapping("login")
    @ResponseBody
    @Ignore
    public ServerResponse login(User user, HttpServletRequest request, HttpServletResponse response){
        return userService.login(user,request,response);
    }
    @RequestMapping("deleteUser")
    @ResponseBody
    public ServerResponse deleteUser(Integer id){

        return userService.deleteUser(id);
    }
    @RequestMapping("loginOut")
    public String loginOut(HttpServletRequest request, HttpServletResponse response){
        userService.loginOut( request,  response);
        return "redirect:/login.jsp";
    }


//发送验证码
    @RequestMapping("sendCode")
    @ResponseBody
    @Ignore
    public ServerResponse sendCode(String phone,HttpServletRequest request){
    return  ServerResponse.success(userService.sendCode(phone,request));
    }

//忘记密码
    @RequestMapping("callBasePassword")
    @ResponseBody
    @Ignore
    public ServerResponse callBasePassword(User user,String code,HttpServletRequest request){
        return userService.callBasePassword(user,code,request);
    }


}
