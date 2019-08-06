package com.example.demo.dto;

import lombok.Data;

/**
 * 购物车
 * @author yudong
 * @create 2019-08-06 9:45
 */

@Data
public class CartDTO {
    /** 商品Id. */
    private String productId;

    /** 数量. */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
