package com.example.demo.service;

import com.example.demo.dto.OrderDTO;

/**
 * @author yudong
 * @create 2019-08-09 17:21
 */
public interface PushMessageService {
    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
