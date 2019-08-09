package com.example.demo.service.impl;

import com.example.demo.dao.SellerInfoRepository;
import com.example.demo.dataobject.SellerInfo;
import com.example.demo.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yudong
 * @create 2019-08-08 17:55
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}

