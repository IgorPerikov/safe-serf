package org.clayman.safe.api.web.controller;

import org.clayman.safe.api.entity.Order;
import org.clayman.safe.api.entity.OrderResult;
import org.clayman.safe.api.service.OrderResultService;
import org.clayman.safe.api.service.OrderService;
import org.clayman.safe.api.utility.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderResultService orderResultService;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity<Order> createOrder(@RequestBody @Valid Order order) {
        return ResponseEntityBuilder.of(orderService.acceptOrder(order));
    }

    @RequestMapping(value = "/order/{id}/result", method = RequestMethod.GET)
    public ResponseEntity<OrderResult> getResult(@PathVariable UUID id) {
        return ResponseEntityBuilder.of(orderResultService.getResult(id));
    }
}
