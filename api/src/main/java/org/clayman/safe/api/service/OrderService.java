package org.clayman.safe.api.service;

import org.clayman.safe.api.entity.Order;
import org.clayman.safe.api.repository.OrderRepository;
import org.clayman.safe.api.service.kafka.OrderMessagingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderMessagingService orderMessagingService;

    @Autowired
    private OrderRepository orderRepository;

    public Order acceptOrder(Order order) {
        order.setId(UUID.randomUUID());
        log.info("Got new order, set id to {}", order.getId().toString());
        handleNewOrder(order);
        return order;
    }

    private void handleNewOrder(Order order) {
        orderRepository.save(order);
        orderMessagingService.sendNewOrderInfo(order);
    }
}
