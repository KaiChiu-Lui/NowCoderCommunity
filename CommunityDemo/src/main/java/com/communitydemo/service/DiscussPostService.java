package com.communitydemo.service;

import com.communitydemo.dao.DiscussPostMapper;
import com.communitydemo.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussPostService {
    @Autowired
    private DiscussPostMapper discussPostMapper;

    //返回对应的分页DisucssPost信息
    //offset limit 通过Controller调用Service方法传参
    //Service层一般只用于调用DAO中的方法并返回业务层的Model
    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit) {
        return discussPostMapper.selectDiscussPosts(userId,offset,limit);
    }

    //获得查询userId时的查询数据行数
    public int findDiscussPostRows(int userId) {
        return discussPostMapper.selectDiscussPostRows(userId);
    }
}
