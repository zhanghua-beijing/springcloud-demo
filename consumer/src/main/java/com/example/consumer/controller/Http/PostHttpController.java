package com.example.consumer.controller.Http;


import com.alibaba.fastjson.JSONObject;

public class PostHttpController {

    /**
     * post 无参
     * @param args
     * @throws Exception
     */
//    public static void main(String[] args) throws Exception {
//        String result = RequestHttpUtil.sendPost("http://localhost:8001/postHttp", "",null);
//        System.out.println(result);
//    }

    /**
     * post 有参, json对象传值
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        JSONObject object = new JSONObject();
        object.put("param","一个参数");
        String result = RequestHttpUtil.sendPost("http://localhost:8001/postHttpParam", object.toJSONString(),null);
        System.out.println(result);
    }

}
