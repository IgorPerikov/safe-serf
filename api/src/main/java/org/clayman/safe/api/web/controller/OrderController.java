package org.clayman.safe.api.web.controller;

import org.clayman.safe.api.entity.Order;
import org.clayman.safe.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Order createOrder(@RequestBody Order order) {
        return null;
    }
}
