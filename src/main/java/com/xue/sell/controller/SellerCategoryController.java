package com.xue.sell.controller;

import com.lly835.bestpay.rest.type.Get;
import com.xue.sell.enums.ResultEnum;
import com.xue.sell.form.CategoryForm;
import com.xue.sell.pojo.ProductCategory;
import com.xue.sell.service.ProductCategoryService;
import com.xue.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by miller on 2018/5/20
 */
@Controller
@Slf4j
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService categoryService;

    /**
     * 类目列表
     *
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(Model model) {
        List<ProductCategory> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        return "category/list";
    }

    @GetMapping("/index")
    public String index(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                        Model model){
        if(categoryId != null){
            ProductCategory category = categoryService.findOne(categoryId);
            model.addAttribute("category",category);
        }
        return "category/index";
    }

    @PostMapping("/save")
    public String save(@Valid CategoryForm form,
                       BindingResult bindingResult,
                       Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("msg",bindingResult.getFieldError().getDefaultMessage());
            model.addAttribute("url", "/sell/seller/category/index");
            return "common/error";
        }
        ProductCategory category = new ProductCategory();
        try {
            if(form.getCategoryId() != null){
                category = categoryService.findOne(form.getCategoryId());
            }
            BeanUtils.copyProperties(form,category);
            categoryService.save(category);
        }catch (Exception e){
            model.addAttribute("msg", ResultEnum.ERROR.getMessage());
            model.addAttribute("url", "/sell/seller/category/index");
            return "common/error";
        }
        model.addAttribute("url", "/sell/seller/category/list");
        return "common/success";
    }
}
