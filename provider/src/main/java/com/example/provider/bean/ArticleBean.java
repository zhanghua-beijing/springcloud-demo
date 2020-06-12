package com.example.provider.bean;/**
 * @Description:
 * @Author: zhanghua
 * @Date: 2020/6/1 14:50
 */

import lombok.Data;

/**
 * @ClassName ArticleBean
 * @Description: TODO
 * @Author zhanghua
 * @Date 2020/6/1 
 * @Version V1.0
 **/
@Data
public class ArticleBean {

    private Integer id;
    private String title;
    private String author;

    public ArticleBean(Integer id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public ArticleBean() {
    }
}
