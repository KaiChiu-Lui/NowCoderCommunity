package com.nowcoder.community.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Data
@ToString
public class User {
    private int id;
    private String username;
    private String password;
    private String salt;
    private String email;
    private int type;
    private int status; // 0 未激活 1 已激活
    private String activationCode;
    private String headerUrl;
    private Date createTime;
}
