package com.example.demo.dao;

import com.example.demo.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yudong
 * @create 2019-08-05 12:26
 */

public interface ProductCategoryDao extends JpaRepository<ProductCategory, Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
//
//    ProductCategory findOne(Integer categoryId);
}
