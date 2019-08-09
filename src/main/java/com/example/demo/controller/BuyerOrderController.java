package com.example.demo.controller;

import com.example.demo.VO.ResultVO;
import com.example.demo.converter.OrderForm2OrderDTOConverter;
import com.example.demo.dto.OrderDTO;
import com.example.demo.enums.ResultEnum;
import com.example.demo.exception.SellException;
import com.example.demo.form.OrderForm;
import com.example.demo.service.BuyerService;
import com.example.demo.service.OrderService;
import com.example.demo.utils.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yudong
 * @create 2019-08-06 15:03
 */
@Slf4j
@RestController
@RequestMapping("/buyer/order")
@Api(value = "买家Controller", tags = {"买家Controller说明"})
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    @ApiOperation(value = "创建订单", notes = "创建订单")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        //表单校验
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());
        return ResultVOUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    @ApiOperation(value = "订单列表", notes = "订单列表")
    public ResultVO list(@ApiParam(name = "openid", value = "用户id",required = true)@RequestParam("openid") String openid,
                         @ApiParam(name = "page", value = "页码")@RequestParam(value = "page", defaultValue = "0") Integer page,
                         @ApiParam(name = "size", value = "数量")@RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        Pageable pageable= PageRequest.of(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageable);
        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    @ApiOperation(value = "订单详情", notes = "订单详情")
    public ResultVO detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {

        return ResultVOUtil.success(buyerService.findOrderOne(openid, orderId));

    }

    //取消订单
    @PostMapping("/cancel")
    @ApiOperation(value = "取消订单", notes = "取消订单")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(openid,orderId);
        return ResultVOUtil.success();
    }
}
