package com.example.demo.service;

import com.example.demo.dataobject.SellerInfo;

/**
 * @author yudong
 * @create 2019-08-08 17:55
 */
public interface SellerService {

    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
