package com.nowcoder.community.controller;

import com.nowcoder.community.entity.Comment;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.CommentService;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private HostHolder hostHolder;

    @RequestMapping("add/{discussId}")
    public String addComment(@PathVariable("discussId")int discussId, Comment comment){
        User user = hostHolder.getUser();
        System.out.println("进入addComment方法");
        if(user==null){ return "redirect:/login";}
        comment.setCreateTime(new Date());
        comment.setUserId(user.getId());
        comment.setStatus(0);
        commentService.addComment(comment);
        return "redirect:/discuss/detail/" + discussId;
    }
}
