package com.example.demo.controller;

import com.example.demo.dto.OrderDTO;
import com.example.demo.enums.ResultEnum;
import com.example.demo.exception.SellException;
import com.example.demo.service.OrderService;
import com.example.demo.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author yudong
 * @create 2019-08-07 15:58
 */
@Controller
@RequestMapping("/pay")
@Api(value = "微信支付Controller", tags = {"微信支付Controller说明"})
public class PayController {
    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;


    @GetMapping("/create")
    @ApiOperation(value = "创建支付订单", notes = "创建支付订单")
    public ModelAndView create(@ApiParam(name = "orderId", value = "订单Id",required = true)@RequestParam("orderId") String orderId,
                               @ApiParam(name = "returnUrl", value = "回调链接",required = true)@RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> map) {
        //1. 查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //2. 发起支付
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);
        return new ModelAndView("pay/create", map);
    }

    /**
     * 微信异步通知
     * @param notifyData
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        payService.notify(notifyData);

        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }
}
