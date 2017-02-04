package org.clayman.safe.api.service;

import org.clayman.safe.api.entity.Order;
import org.clayman.safe.api.repository.OrderRepository;
import org.clayman.safe.api.service.kafka.OrderMessagingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutdatedOrdersService {

    private static final Logger log = LoggerFactory.getLogger(OutdatedOrdersService.class);

    @Autowired
    private OrderMessagingService orderMessagingService;

    @Autowired
    private OrderRepository orderRepository;

    @Scheduled(fixedDelay = 60_000L)
    public void proceedOutdatedOrders() {
        log.debug("Start findind outdated orders");
        List<Order> outdatedOrders = orderRepository.findOutdatedOrders();
        if (outdatedOrders.size() != 0) {
            log.info("Found {} outdated orders", outdatedOrders.size());
        } else {
            log.debug("Found 0 outdated orders");
        }
        for (Order outdatedTask : outdatedOrders) {
            orderMessagingService.sendNewOrderInfo(outdatedTask);
        }
        log.debug("Complete processing outdated orders");
    }
}
