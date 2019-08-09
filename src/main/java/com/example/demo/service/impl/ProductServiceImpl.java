package com.example.demo.service.impl;

import com.example.demo.dao.ProductInfoDao;
import com.example.demo.dataobject.ProductInfo;
import com.example.demo.dto.CartDTO;
import com.example.demo.enums.ProductStatusEnum;
import com.example.demo.enums.ResultEnum;
import com.example.demo.exception.SellException;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author yudong
 * @create 2019-08-05 15:00
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoDao repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            Optional optional = repository.findById(cartDTO.getProductId());
            if(optional.isPresent()){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }else {
                ProductInfo productInfo = (ProductInfo)optional.get();
                Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
                productInfo.setProductStock(result);
                repository.save(productInfo);
            }

        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            Optional optional = repository.findById(cartDTO.getProductId());
            if(!optional.isPresent()){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }else {
                ProductInfo productInfo = (ProductInfo)optional.get();
                Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
                if(result < 0){
                    throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
                }
                productInfo.setProductStock(result);
                repository.save(productInfo);
            }

        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        Optional optional = repository.findById(productId);
        if(!optional.isPresent()){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }else {
            ProductInfo productInfo = (ProductInfo)optional.get();
            if(productInfo.getProductStatusEnum().equals(ProductStatusEnum.UP)){
                throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
            }
            productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
            return repository.save(productInfo);
        }
    }

    @Override
    public ProductInfo offSale(String productId) {
        Optional optional = repository.findById(productId);
        if(!optional.isPresent()){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }else {
            ProductInfo productInfo = (ProductInfo)optional.get();
            if(productInfo.getProductStatusEnum().equals(ProductStatusEnum.DOWN)){
                throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
            }
            productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
            return repository.save(productInfo);
        }
    }

}
