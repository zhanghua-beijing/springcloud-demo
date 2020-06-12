package com.example.provider.controller;/**
 * @Description:
 * @Author: zhanghua
 * @Date: 2020/6/10 13:50
 */

import com.alibaba.fastjson.JSON;
import com.example.provider.bean.ArticleBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @ClassName EsArticleDocController
 * @Description: TODO
 * @Author zhanghua
 * @Date 2020/6/10 
 * @Version V1.0
 **/
@Api(tags = "provider_Es接口doc")
@Controller
public class EsArticleDocController {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @ResponseBody
    @ApiOperation(value = "1.Provider_es中add文档")
    @GetMapping("/addDocToEs")
    public String addDocToEs(String index, Integer id, String author, String title){

        ArticleBean article = new ArticleBean();
        article.setId(id);
        article.setAuthor(author);
        article.setTitle(title);

        IndexRequest request = new IndexRequest(index);

        // put /article_index/_doc/1
//        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(10));
        //将资源放进
        request.source(JSON.toJSONString(article), XContentType.JSON);

        //客户端请求
        try {
            IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            return indexResponse + "";
        } catch (IOException e) {
            e.printStackTrace();
            return "IO错误";
        }
    }

    @ResponseBody
    @ApiOperation(value = "2.Provider_es中获取文档")
    @GetMapping("/getDocFromEs")
    public String getDocFromEs(String index, Integer id) throws IOException {

        GetRequest getRequest = new GetRequest(index,id.toString());
        boolean exists = false;
        //客户端请求
        exists = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);

        if(exists){
            GetResponse documentFields = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
            return documentFields.getSource() +"";
        }else {
            return "文档不存在";
        }
    }

    @ResponseBody
    @ApiOperation(value = "3.Provider_es中更新文档")
    @GetMapping("/updateDocFromEs")
    public String updateDocFromEs(String index, Integer id) throws IOException {

        UpdateRequest updateRequest = new UpdateRequest(index, id.toString());
        updateRequest.timeout(TimeValue.timeValueSeconds(10));
        //将资源放进
        ArticleBean article = new ArticleBean();
        article.setId(11);
        article.setAuthor("新author");
        article.setTitle("新title");
        updateRequest.doc(JSON.toJSONString(article),XContentType.JSON);
        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        return updateResponse+"";
    }

    @ResponseBody
    @ApiOperation(value = "4.Provider_es中删除文档")
    @GetMapping("/deleteDocFromEs")
    public String deleteDocFromEs(String index, Integer id) throws IOException {

        DeleteRequest deleteRequest = new DeleteRequest(index, id.toString());
        deleteRequest.timeout(TimeValue.timeValueSeconds(10));

        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        return deleteResponse+"";
    }
}
