package com.example.demo.service;

import com.example.demo.dto.OrderDTO;

/**
 * @author yudong
 * @create 2019-08-06 16:17
 */
public interface BuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
