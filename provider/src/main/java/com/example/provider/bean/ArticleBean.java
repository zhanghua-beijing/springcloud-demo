package com.example.provider.bean;/**
 * @Description:
 * @Author: zhanghua
 * @Date: 2020/6/1 14:50
 */

import io.searchbox.annotations.JestId;
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

    @JestId
    private Integer id;
    private String title;
    private String author;

}
