package com.redant.example.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.redant.core.bean.annotation.Autowired;
import com.redant.core.bean.annotation.Bean;
import com.redant.core.common.enums.RequestMethod;
import com.redant.core.render.RenderType;
import com.redant.core.controller.annotation.Controller;
import com.redant.core.controller.annotation.Mapping;
import com.redant.core.controller.annotation.Param;
import com.redant.example.service.user.IUserService;
import com.redant.example.service.user.UserBean;

/**
 * @author houyi.wh
 * @date 2017/12/1
 **/
@Bean
@Controller(path="/user")
public class UserController {

    /**
     * 如果需要使用Autowired，则该类自身需要使用Bean注解标注
     */
    @Autowired(name="userService")
    private IUserService userService;

    @Mapping(path="/info",requestMethod=RequestMethod.GET,renderType=RenderType.JSON)
    public UserBean getUserInfo(@Param(key="id",checkNull=true) Integer id){
        return userService.selectUserInfo(id);
    }

    @Mapping(path="/list",requestMethod=RequestMethod.GET,renderType=RenderType.JSON)
    public JSONArray getUserList(){
        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();
        UserBean user = new UserBean();
        user.setId(23);
        user.setUserName("wang");
        object.put("user",user);
        array.add(object);
        return array;
    }

    @Mapping(path="/count",requestMethod=RequestMethod.GET,renderType=RenderType.JSON)
    public JSONObject getUserCount(){
        JSONObject object = new JSONObject();
        int count = userService.selectCount();
        object.put("count",count);
        return object;
    }

}