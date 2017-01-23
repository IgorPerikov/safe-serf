package org.clayman.safe.api.service.kafka;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.clayman.safe.api.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderMessagingService {

    private static final String TOPIC_NAME = "orders";

    @Autowired
    private Producer<String, String> producer;

    public void sendNewOrderInfo(Order order) {
        producer.send(new ProducerRecord<>(TOPIC_NAME, order.getId().toString(), order.getUrl()));
    }
}
