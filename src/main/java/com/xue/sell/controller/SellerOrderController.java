package com.xue.sell.controller;

import com.xue.sell.dto.OrderDTO;
import com.xue.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 卖家端订单
 * Created by miller on 2018/5/12
 */
@Controller
@RequestMapping("/seller")
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     * @param page 第几页 从第一页开始
     * @param size 一页有多少条数据
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                       Model model) {
        Page<OrderDTO> orderDTOPage = orderService.findList(new PageRequest(page - 1, size));
        model.addAttribute("orderDTOPage",orderDTOPage);
        model.addAttribute("currentPage",page);
        return "order/list";
    }
}
