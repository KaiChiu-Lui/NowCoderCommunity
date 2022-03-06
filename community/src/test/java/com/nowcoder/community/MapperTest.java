package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.entity.DiscussPost;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTest {
    @Autowired
    DiscussPostMapper discussPostMapper;

    @Test
    public void discussPostMapperTest(){
        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(0, 1, 10);
        System.out.println(discussPosts);
    }
}
