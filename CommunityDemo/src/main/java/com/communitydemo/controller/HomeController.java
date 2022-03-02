package com.communitydemo.controller;


import com.communitydemo.entity.DiscussPost;
import com.communitydemo.entity.Page;
import com.communitydemo.service.DiscussPostService;
import com.communitydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    DiscussPostService discussPostService;

    @Autowired
    UserService userService;

    //注入service层 controller主要调用service层实现业务逻辑 并封装成用户层model返回给视图
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page) {
        //
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");
        //将查询的数据装入map中返回给视图 视图根据返回的map.key访问对应数据
        //一个Map<String,Object>对应一条帖子 存储"user"和"post"信息
        //List<Map>表示全部帖子
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        List<DiscussPost> list = discussPostService.findDiscussPosts(0,page.getOffset(),page.getLimit());
        if(list!=null){
            //一条帖子是一个map
            for(DiscussPost discussPost : list){
                Map<String,Object> map = new HashMap<>() ;
                map.put("post",discussPost);
                map.put("user",userService.findUserById(discussPost.getUserId()));
                discussPosts.add(map);
            }
        }
        model.addAllAttributes(list);
        return "/index";
    }
}
