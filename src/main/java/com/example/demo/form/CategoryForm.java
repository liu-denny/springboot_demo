package com.example.demo.form;

import lombok.Data;

/**
 * @author yudong
 * @create 2019-08-08 16:31
 */
@Data
public class CategoryForm {
    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
