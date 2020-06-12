package com.example.provider.controller;/**
 * @Description:
 * @Author: zhanghua
 * @Date: 2020/6/1 15:03
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @ClassName EsArticleController
 * @Description: TODO
 * @Author zhanghua
 * @Date 2020/6/1 
 * @Version V1.0
 **/
@Api(tags = "provider_Es接口Index")
@RestController
public class EsArticleIndexController {


    @Autowired
    @Qualifier("restHighLevelClient")
    RestHighLevelClient client;

    @ApiOperation(value = "1.Provider_es中add索引")
    @GetMapping("/addArticleToEs")
    public void addArticleToEs(){


        CreateIndexRequest createIndexRequest = new CreateIndexRequest("article_index");

        try {
            CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            System.out.println(createIndexRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "2.Provider_es中检查索引是否存在")
    @GetMapping("/getIndexFromEs")
    public void getIndexFromEs(){

        GetIndexRequest getIndexRequest = new GetIndexRequest("my_article_index_01");
        try {
            boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
            System.out.println(exists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "3.Provider_es中删除指定索引")
    @GetMapping("/DelIndexFromEs")
    public void DelIndexFromEs(){

        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("my_article_index_01");
        try {
            AcknowledgedResponse delete = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
            System.out.println(delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
