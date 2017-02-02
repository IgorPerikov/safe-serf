package org.clayman.safe.background.service.kafka;

import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;
import java.util.UUID;

public class UUIDDeserializer implements Deserializer<UUID> {

    @Override
    public UUID deserialize(String topic, byte[] data) {
        String stringUUID = new String(data);
        return UUID.fromString(stringUUID);
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}

    @Override
    public void close() {}
}
