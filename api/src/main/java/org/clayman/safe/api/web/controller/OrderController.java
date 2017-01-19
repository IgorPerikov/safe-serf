package org.clayman.safe.api.web.controller;

import org.clayman.safe.api.entity.Order;
import org.clayman.safe.api.entity.OrderResult;
import org.clayman.safe.api.service.OrderResultService;
import org.clayman.safe.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderResultService orderResultService;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public Order createOrder(@RequestBody Order order) {
        return orderService.acceptNewOrder(order);
    }

    @RequestMapping(value = "/order/{id}/result", method = RequestMethod.GET)
    public OrderResult getResult(@PathVariable UUID id) {
        return orderResultService.getResult(id);
    }
}
