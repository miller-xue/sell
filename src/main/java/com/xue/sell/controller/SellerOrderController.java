package com.xue.sell.controller;

import com.xue.sell.dto.OrderDTO;
import com.xue.sell.enums.ResultEnum;
import com.xue.sell.exception.SellException;
import com.xue.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 卖家端订单
 * Created by miller on 2018/5/12
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     *
     * @param page  第几页 从第一页开始
     * @param size  一页有多少条数据
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "1") Integer size,
                       Model model) {
        Page<OrderDTO> orderDTOPage = orderService.findList(new PageRequest(page - 1, size));
        model.addAttribute("orderDTOPage", orderDTOPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        return "order/list";
    }


    @GetMapping("/cancel/{orderId}")
    public String cancel(@PathVariable("orderId") String orderId,
                         Model model) {
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        } catch (SellException e) {
            log.error("【卖家端取消订单】 发生异常", e);
            model.addAttribute("msg",e.getMessage());
            model.addAttribute("url","/sell/seller/order/list");
            return "common/error";
        }
        model.addAttribute("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        model.addAttribute("url","/sell/seller/order/list");
        return "common/success";
    }

    /**
     * 订单详情
     * @param orderId
     * @param model
     * @return
     */
    @GetMapping("/detail/{orderId}")
    public String detail(@PathVariable String orderId,
                         Model model){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            model.addAttribute("orderDTO",orderDTO);
            return "order/detail";
        } catch (SellException e) {
            log.error("【卖家端订单详情】 发生异常", e);
            model.addAttribute("msg",e.getMessage());
            model.addAttribute("url","/sell/seller/order/list");
            return "common/error";
        }
    }


    @GetMapping("/finish/{orderId}")
    public String finished(@PathVariable String orderId,
                           Model model){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);

            model.addAttribute("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
            model.addAttribute("url","/sell/seller/order/list");
            return "common/success";
        } catch (SellException e) {
            log.error("【卖家端订单完结】 发生异常", e);
            model.addAttribute("msg",e.getMessage());
            model.addAttribute("url","/sell/seller/order/list");
            return "common/error";
        }
    }
}
