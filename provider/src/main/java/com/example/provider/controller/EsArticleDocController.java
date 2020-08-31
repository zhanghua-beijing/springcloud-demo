package com.example.provider.controller;/**
 * @Description:
 * @Author: zhanghua
 * @Date: 2020/6/10 13:50
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.provider.bean.ArticleBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
        //不给id，默认无序ID
//        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(10));
        //将资源放进
        request.source(JSON.toJSONString(article), XContentType.JSON);

        //客户端请求
        try {
            IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            return indexResponse.toString() + "";
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
            return documentFields.toString() +"";
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
        return updateResponse.toString() +"";
    }

    @ResponseBody
    @ApiOperation(value = "4.Provider_es中删除文档")
    @GetMapping("/deleteDocFromEs")
    public String deleteDocFromEs(String index, Integer id) throws IOException {

        DeleteRequest deleteRequest = new DeleteRequest(index, id.toString());
        deleteRequest.timeout(TimeValue.timeValueSeconds(10));

        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        return deleteResponse.toString() +"";
    }

    @ResponseBody
    @ApiOperation(value = "5.Provider_es中json批量添加文档")
    @PostMapping("/bulkAddDocToEs")
    public String bulkAddDocToEs(@RequestBody String jsonParam) throws IOException {

        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout(TimeValue.timeValueSeconds(10));

        JSONObject jsonObjectNew = JSONObject.parseObject(jsonParam);
        String index = jsonObjectNew.getString("index");
        String data = jsonObjectNew.getString("data");

        JSONArray dataArray = JSONArray.parseArray(data);

        for (int i = 0; i < dataArray.size(); i++) {
            bulkRequest.add(new IndexRequest(index)
//                    .id("")
                    .source(JSON.toJSONString(dataArray.get(i)),XContentType.JSON));
        }
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);


        return bulkResponse.toString() +"";
    }

    @ResponseBody
    @ApiOperation(value = "6.Provider_es中批量添加articleBean")
    @PostMapping("/bulkAddArticleToEs")
    public String bulkAddArticleToEs() throws IOException {

        BulkResponse bulkResponse = null;

        ArticleBean article1 = new ArticleBean(1,"第1个文章","第1个作者");
        ArticleBean article2 = new ArticleBean(2,"第2个文章","第2个作者");
        ArticleBean article3 = new ArticleBean(3,"第3个文章","第3个作者");
        ArticleBean article4 = new ArticleBean(4,"第4个文章","第4个作者");

        List<ArticleBean> articleBeanList = new ArrayList<ArticleBean>();
        articleBeanList.add(article1);
        articleBeanList.add(article2);
        articleBeanList.add(article3);
        articleBeanList.add(article4);

        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout(TimeValue.timeValueSeconds(10));

        for(int i=0;i<articleBeanList.size();i++){
            bulkRequest.add(new IndexRequest("index_name")
//                    .id("")
                    .source(JSON.toJSONString(articleBeanList.get(i)),XContentType.JSON));
            if(bulkRequest.requests().size()==2){//批量每次存2个对象
                bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
                bulkRequest.requests().clear();//存完后清空，再存
            }

        }
        return bulkResponse.toString() +"";
    }

    @ResponseBody
    @ApiOperation(value = "6.Provider_es中大查询")
    @PostMapping("/searchFromEs")
    public String searchFromEs(@RequestBody String jsonParam) throws IOException {

        JSONObject jsonObjectNew = JSONObject.parseObject(jsonParam);

        String index = jsonObjectNew.getString("index");
        SearchRequest searchRequest = new SearchRequest(index);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(10));
//        String data = jsonObjectNew.getString("data");
        TermQueryBuilder termQuery = QueryBuilders.termQuery("name", "444");

        searchSourceBuilder.query(termQuery);

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);


        return searchResponse.toString() +"";
    }


    public void js(){

        ArticleBean article = new ArticleBean(1,"我是一根葱", "my");

        //Java对象转化为JSON对象 (Java --> JsonObject)
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(article);

        //JSON对象转换成Java对象 (JsonObject --> Java)
        ArticleBean article1 = JSONObject.toJavaObject(jsonObject, ArticleBean.class);

        //Java对象转换成JSON字符串 (Java --> String)
        String articleString = JSONObject.toJSONString(article);

        //JSON字符串转换成JSON对象 (String --> JsonObject)
        JSONObject jsonObjectNew = JSONObject.parseObject(articleString);

        //JSON字符串转换成Java对象 (String --> Java)
        ArticleBean article2 = JSONObject.parseObject(articleString, ArticleBean.class);

        //JSON字符串转换成List对象 (String --> List)
        List<ArticleBean> list = JSONObject.parseArray(articleString,  ArticleBean.class);
    }
}
