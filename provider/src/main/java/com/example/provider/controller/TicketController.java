package com.example.provider.controller;/**
 * @Description:
 * @Author: zhanghua
 * @Date: 2020/5/25 18:40
 */

import com.example.provider.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TicketController
 * @Description: TODO
 * @Author zhanghua
 * @Date 2020/5/25 
 * @Version V1.0
 **/
@Api(tags = "Ticket接口")
@RestController
public class TicketController {

    @Autowired
    TicketService ticketService;

    @ApiOperation(value = "1.ticket提供接口")
    @GetMapping("/getTicket")
    public String getTicket(){
        System.out.println("我被调用了");
        return ticketService.getTicket();
    }

    @ApiOperation(value = "11.ticket提供接口")
    @GetMapping("/getTicket1")
    public String getTicket1(){
        System.out.println("我被调用了");
        return ticketService.getTicket();
    }
}
