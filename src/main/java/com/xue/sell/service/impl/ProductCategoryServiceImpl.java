package com.xue.sell.service.impl;

import com.xue.sell.enums.ResultEnum;
import com.xue.sell.exception.CategoryException;
import com.xue.sell.exception.ProductException;
import com.xue.sell.exception.SellException;
import com.xue.sell.pojo.ProductCategory;
import com.xue.sell.repository.ProductCategoryRepository;
import com.xue.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by miller on 2018/5/5
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        ProductCategory result = null;
        try {
            result = repository.save(productCategory);
        }catch (Exception e){
            throw new CategoryException(ResultEnum.INNER_ERROR);
        }
        return result;
    }
}
