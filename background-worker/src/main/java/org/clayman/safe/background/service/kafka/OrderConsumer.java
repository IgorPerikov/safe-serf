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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

@Service
public class OrderConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderConsumer.class);

    @Autowired
    private SafeCheckService safeCheckService;

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private Consumer<UUID, String> consumer;

    @Scheduled(fixedDelay = 3_000L)
    public void startConsuming() {
        log.debug("Consuming data from kafka");
        ConsumerRecords<UUID, String> records = consumer.poll(0L);
        List<CompletableFuture<Void>> tasks = new ArrayList<>();
        for (ConsumerRecord<UUID, String> record : records) {
            CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
                safeCheckService.handle(record.key(), record.value());
            }, executorService);
            tasks.add(cf);
        }
        log.debug("Complete sending tasks to executor");

        CompletableFuture<Void> cfBarrier = CompletableFuture.allOf(tasks.toArray(new CompletableFuture[tasks.size()]));
        try {
            cfBarrier.get();
        } catch (ExecutionException ignored) {
            // fallback already provided by task logic
        } catch (InterruptedException ignored) {

        }

        log.debug("Complete handling orders");
    }
}
