package com.example.provider.controller.Http;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "案例GetHttp")
@RestController
public class GetHttpController {

    @ApiOperation(value = "1.getHttp")
    @GetMapping("/getHttp")
    public String getHttp(HttpServletRequest request){
        String key = request.getHeader("appkey");
        System.out.println(key);
        return "get无参调用成功";
    }

    @ApiOperation(value = "2.getHttpParam")
    @GetMapping("/getHttpParam")
    public String getHttpParam(String param){
        return "get有参调用成功，参数为:"+param;
    }
}
