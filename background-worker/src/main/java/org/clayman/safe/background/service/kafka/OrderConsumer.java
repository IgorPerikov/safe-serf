package org.clayman.safe.background.service.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.clayman.safe.background.service.SafeCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderConsumer.class);

    @Autowired
    private SafeCheckService safeCheckService;

    @Autowired
    private Consumer<String, String> consumer;

    @Scheduled(fixedDelay = 60_000L)
    public void startConsuming() {
        log.info("Consuming data from Kafka");
        ConsumerRecords<String, String> records = consumer.poll(0L);
        for (ConsumerRecord<String, String> record : records)
            safeCheckService.handle(record.key(), record.value());
    }
}
