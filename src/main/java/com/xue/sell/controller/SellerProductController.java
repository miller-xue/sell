package com.xue.sell.controller;

import com.xue.sell.exception.ProductException;
import com.xue.sell.pojo.ProductInfo;
import com.xue.sell.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by miller on 2018/5/19
 */
@Controller
@Slf4j
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;

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
}
