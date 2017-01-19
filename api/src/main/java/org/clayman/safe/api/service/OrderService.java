package org.clayman.safe.api.service;

import org.clayman.safe.api.entity.Order;
import org.clayman.safe.api.kafka.OrderMessagingService;
import org.clayman.safe.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMessagingService orderMessagingService;

    public Order acceptNewOrder(Order order) {
        order.setId(UUID.randomUUID());
        // TODO: check if available in cache
        orderRepository.save(order);
        orderMessagingService.sendNewOrderInfo(order);
        return order;
    }
}
