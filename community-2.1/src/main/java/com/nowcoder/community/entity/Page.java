package com.nowcoder.community.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Data
@ToString
public class Page {

    private int current = 1;

    // 显示上限
    private int limit = 10;

    // 数据总数(用于计算总页数)
    private int rows;

    // 查询路径(用于复用分页链接)
    private String path;

    //获得当前页起始行 limit M,N的M是从0开始 所以要current先-1
    public int getOffset(){
        return (current-1)*limit;
    }

    //获得总行数
    public int getTotal(){
        if(rows%limit==0){
            return rows/limit;
        }
        else{
            return rows/limit+1;
        }
    }

    //起始页码 默认为前两页 否则为第一页
    public int getFrom(){
        int result = current-2;
        return result>0?result:1;
    }

    //结束页码
    public int getTo(){
        int result = current+2;
        return result<=this.getTotal()?result:this.getTotal();
    }

}
