package org.clayman.safe.api.service.kafka;

import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;
import java.util.UUID;

public class UUIDSerializer implements Serializer<UUID> {

    @Override
    public byte[] serialize(String topic, UUID data) {
        return data.toString().getBytes();
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}

    @Override
    public void close() {}
}
