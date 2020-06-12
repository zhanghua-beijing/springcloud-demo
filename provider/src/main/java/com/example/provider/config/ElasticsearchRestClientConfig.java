package com.example.provider.config;/**
 * @Description:
 * @Author: zhanghua
 * @Date: 2020/6/5 15:59
 */

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName ElasticsearchRestClientConfig
 * @Description: TODO
 * @Author zhanghua
 * @Date 2020/6/5 
 * @Version V1.0
 **/
@Configuration
public class ElasticsearchRestClientConfig {

    //自定义连接属性
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.36.25", 9200, "http")));
        return client;
    }

}
