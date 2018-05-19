package com.xue.sell.controller;

import com.xue.sell.enums.ResultEnum;
import com.xue.sell.exception.ProductException;
import com.xue.sell.form.ProductForm;
import com.xue.sell.pojo.ProductCategory;
import com.xue.sell.pojo.ProductInfo;
import com.xue.sell.service.ProductCategoryService;
import com.xue.sell.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by miller on 2018/5/19
 */
@Controller
@Slf4j
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService categoryService;

    /**
     * 商品
     *
     * @param page  第几页 从第一页开始
     * @param size  一页有多少条数据
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "1") Integer size,
                       Model model) {
        Page<ProductInfo> productInfoPage = productInfoService.findAll(new PageRequest(page-1,size));
        model.addAttribute("currentPage",page);
        model.addAttribute("size",size);
        model.addAttribute("productInfoPage",productInfoPage);
        return "product/list";
    }

    /**
     * 商品上架
     * @param productId
     * @param model
     * @return
     */
    @GetMapping("/on_sale")
    public String onSale(@RequestParam("productId") String productId,
                         Model model) {
       try {
           productInfoService.onSale(productId);
           model.addAttribute("url", "/sell/seller/product/list");
           return "common/success";
       }catch (ProductException e){
           model.addAttribute("msg",e.getMessage());
           model.addAttribute("url", "/sell/seller/product/list");
           return "common/error";
       }
    }/**
     * 商品下架
     * @param productId
     * @param model
     * @return
     */
    @GetMapping("/off_sale")
    public String offSale(@RequestParam("productId") String productId,
                         Model model) {
       try {
           productInfoService.offSale(productId);
           model.addAttribute("url", "/sell/seller/product/list");
           return "common/success";
       }catch (ProductException e){
           model.addAttribute("msg",e.getMessage());
           model.addAttribute("url", "/sell/seller/product/list");
           return "common/error";
       }
    }


    @GetMapping("/index")
    public String index(@RequestParam(value = "productId", required = false) String productId,
                        Model model) {

        //查询商品
        if (StringUtils.isNotEmpty(productId)) {
            ProductInfo productInfo = productInfoService.findOne(productId);
            if (productInfo == null) {
            }
            model.addAttribute("productInfo", productInfo);
        }
        //查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        return "product/index";
    }

    /**
     * 保存/更新
     * @param productForm
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/save")
    public String save(@Valid ProductForm productForm,
                       BindingResult bindingResult,
                       Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("msg",bindingResult.getFieldError().getDefaultMessage());
            model.addAttribute("url", "/sell/seller/product/index");
            return "common/error";
        }
        productInfoService.findOne(productForm.getProductId());

        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(productForm,productInfo);
        try {
           ProductInfo result = productInfoService.save(productInfo);
        }catch (Exception e){
            model.addAttribute("msg", ResultEnum.ERROR.getMessage());
            model.addAttribute("url", "/sell/seller/product/index");
            return "common/error";
        }

        model.addAttribute("url", "/sell/seller/product/list");
        return "common/success";
    }
}
