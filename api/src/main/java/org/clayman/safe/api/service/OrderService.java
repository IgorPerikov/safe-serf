package org.clayman.safe.api.service;

import org.clayman.safe.api.entity.Order;
import org.clayman.safe.api.repository.OrderRepository;
import org.clayman.safe.api.service.kafka.OrderMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderMessagingService orderMessagingService;

    @Autowired
    private OrderRepository orderRepository;

    public Order acceptOrder(Order order) {
        order.setId(UUID.randomUUID());
        handleNewOrder(order);
        return order;
    }

    private void handleNewOrder(Order order) {
        orderRepository.save(order);
        orderMessagingService.sendNewOrderInfo(order);
    }
}
