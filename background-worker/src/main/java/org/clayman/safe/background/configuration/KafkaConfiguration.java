package org.clayman.safe.background.configuration;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.clayman.safe.background.service.kafka.UUIDDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Properties;
import java.util.UUID;

@Configuration
public class KafkaConfiguration {

    private static final String TOPIC_NAME = "orders";

    @Bean(destroyMethod = "close")
    public Consumer<UUID, String> consumer() {
        KafkaConsumer<UUID, String> consumer = new KafkaConsumer<>(buildKafkaConsumerProperties());
        consumer.subscribe(Collections.singletonList(TOPIC_NAME));
        return consumer;
    }

    private Properties buildKafkaConsumerProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "order_consumer");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", UUIDDeserializer.class.getName());
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }
}
