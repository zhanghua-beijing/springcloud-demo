package com.example.consumer.controller.Http;


import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class GetHttpController {


    /**
     * get 无参
     */
    public static void main(String[] args) throws Exception {
        String result = RequestHttpUtil.sendGet("http://localhost:8001/getHttp", null);
        System.out.println(result);
    }

    /**
     * get 有参
     */
//    public static void main(String[] args) throws Exception {
//        String param = URLEncoder.encode("大家好");
//        String result = RequestHttpUtil.sendGet("http://localhost:8001/getHttpParam?param="+param, null);
//        System.out.println(result);
//    }




}
