package com.communitydemo.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Page {
    private int current = 1; //当前页码
    private int rows; //总行数 调用service方法获取
    private int limit = 10;//显示行数
    private String path; //路径

    public void setCurrent(int current) {
        if (current >= 1) {
            this.current = current;
        }
    }

    public void setRows(int rows) {
        if (rows >= 0) {
            this.rows = rows;
        }
    }

    /**
     * 获得页首行
     */
    public int getOffset(){
        return (current-1)*limit;
    }

    /**
     * 获得总页数
     * @return
     */
    public int getTotal(){
        if(rows%limit==0){
            return rows/limit;
        }
        else return rows/limit+1;
    }

    /**
     * 获得展示页数开始页
     * @return
     */
    public int getFrom(){
        if(current>2){
            return current -2;
        }
        else{
            return 1;
        }
    }

    /**
     * 获得展示页面结束页
     * @return
     */
    public int getTo() {
        int to = current + 2;
        int total = getTotal();
        return to > total ? total : to;
    }
}
