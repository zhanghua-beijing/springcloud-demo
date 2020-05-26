package com.example.consumer.controller;/**
 * @Description:
 * @Author: zhanghua
 * @Date: 2020/5/25 18:40
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName TicketController
 * @Description: TODO
 * @Author zhanghua
 * @Date 2020/5/25 
 * @Version V1.0
 **/
@Api(tags = "Ticket调用接口")
@RestController
public class TicketController {


    @Autowired
    RestTemplate restTemplate;

    @ApiOperation(value = "1.获取ticket")
    @GetMapping("/findTicket")
    public String findTicket(){
        String ticket = restTemplate.getForObject("http://PROVITER/getTicket", String.class);
        return ticket;
    }
}
