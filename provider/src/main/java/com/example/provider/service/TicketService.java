package com.example.provider.service;/**
 * @Description:
 * @Author: zhanghua
 * @Date: 2020/5/25 18:39
 */

import org.springframework.stereotype.Service;

/**
 * @ClassName TicketService
 * @Description: TODO
 * @Author zhanghua
 * @Date 2020/5/25 
 * @Version V1.0
 **/
@Service
public class TicketService {

    public String getTicket(){
        return "获取成功";
    }
}
