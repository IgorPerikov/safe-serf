package org.clayman.safe.background.service;

import org.clayman.safe.background.api.SafeApiClient;
import org.clayman.safe.background.entity.OrderResult;
import org.clayman.safe.background.entity.Status;
import org.clayman.safe.background.repository.OrderResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

@Service
public class SafeCheckService {

    @Autowired
    private SafeApiClient safeApiClient;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private OrderResultRepository orderResultRepository;

    public void handle(String orderUuid, String url) {
        Status status;
        if (cacheService.contains(url)) {
            status = cacheService.get(url);
        } else {
            try {
                status = safeApiClient.checkUrl(url);
            } catch (IOException e) {
                // TODO: retry later?
                return;
            }
            cacheService.put(url, status);
        }
        OrderResult orderResult = new OrderResult();
        orderResult.setCheckDate(Instant.now());
        orderResult.setId(UUID.fromString(orderUuid));
        orderResult.setStatus(status);
        orderResult.setUrl(url);
        orderResultRepository.save(orderResult);
    }
}
