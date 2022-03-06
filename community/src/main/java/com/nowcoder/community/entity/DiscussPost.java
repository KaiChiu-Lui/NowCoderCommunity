package com.nowcoder.community.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
@ToString
@Repository
public class DiscussPost {

    private int id;
    private int userId;
    private String title;
    private String content;
    private int type; // 1表示置顶
    private int status; // 1表示精华
    private Date createTime;
    private int commentCount;
    private double score;

}
