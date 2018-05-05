package com.xue.sell.controller;

import com.xue.sell.VO.ProductInfoVO;
import com.xue.sell.VO.ProductVO;
import com.xue.sell.VO.ResultVO;
import com.xue.sell.pojo.ProductCategory;
import com.xue.sell.pojo.ProductInfo;
import com.xue.sell.service.ProductCategoryService;
import com.xue.sell.service.ProductInfoService;
import com.xue.sell.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by miller on 2018/5/5
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productService;

    @Autowired
    private ProductCategoryService categoryService;

    @GetMapping("/list")
    public ResultVO<List<ProductVO>> list(){
        //1.查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //2.查询类目(一次性查询)
//        List<Integer> categoryTypeList = new ArrayList<>();
        //传统方法
//        for (ProductInfo productInfo :productInfoList){
//            categoryTypeList.add(productInfo.getCategoryType());
//        }
        //精简方法(java8 lambda表达撒)
        List<Integer> categoryTypeList = productInfoList.stream()
                                        .map(e -> e.getCategoryType())
                                        .collect(Collectors.toList());
        List<ProductCategory>  productCategoryList =  categoryService.findByCategoryTypeIn(categoryTypeList);


        List<ProductVO> productVOList = new ArrayList<>();
        //3.数据拼装
        for (ProductCategory category : productCategoryList){
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList){
                if(category.getCategoryType() != productInfo.getCategoryType()){
                    break;
                }
                ProductInfoVO productInfoVO = new ProductInfoVO();
                BeanUtils.copyProperties(productInfo,productInfoVO);
                productInfoVOList.add(productInfoVO);
            }
            ProductVO productVO = new ProductVO(category.getCategoryName(),category.getCategoryType(),productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }
}
