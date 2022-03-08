package com.nowcoder.community.controller;


import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//首页功能控制器
@Controller
public class HomeController {
    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page) {
        //从/index页面传入page的参数
        page.setPath("/index");
        page.setRows(discussPostService.findDiscussPostRows(0));

        //使用List<Map>封装discussPost 其中一个Map对应一条DisucssPost
        List<Map<String,Object>>discussPosts = new ArrayList<>();
        List<DiscussPost> lists = discussPostService.findDiscussPosts(0,page.getOffset(),page.getLimit());
        if(lists!=null){
            for(DiscussPost discussPost : lists){
                Map<String,Object> map = new HashMap<>(); // 一个map对应一条记录 {user:,post:}
                map.put("post",discussPost);
                map.put("user",userService.findUserById(discussPost.getUserId()));
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts", discussPosts);
        return "/index";
    }

}
