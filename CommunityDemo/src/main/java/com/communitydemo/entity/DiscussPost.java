package com.communitydemo.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ToString
@Data
public class DiscussPost {
    private int id;
    private int userId;
    private String title;
    private String content;
    private int type;
    private int status; //2表示被拉黑
    private Date createTime;
    private int commentCount;
    private double score;
}
