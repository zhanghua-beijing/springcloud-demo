package com.example.provider.controller;/**
 * @Description:
 * @Author: zhanghua
 * @Date: 2020/6/1 15:03
 */

import com.example.provider.bean.ArticleBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName EsArticleController
 * @Description: TODO
 * @Author zhanghua
 * @Date 2020/6/1 
 * @Version V1.0
 **/
@Api(tags = "provider_Es接口")
@RestController
public class EsArticleController {


    @Autowired
    RestHighLevelClient restHighLevelClient;

    @ApiOperation(value = "1.Provider_es中add文章")
    @GetMapping("/addArticleToEs")
    public void addArticleToEs(){
        ArticleBean article = new ArticleBean();
        article.setId(1);
        article.setAuthor("罗永浩");
        article.setTitle("这是一个标题");

//        Index index = new Index.Builder(article).index("PDMI").type("article").build();

    }
}
