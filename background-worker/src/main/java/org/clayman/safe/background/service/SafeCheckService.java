package org.clayman.safe.background.service;

import org.clayman.safe.background.api.SafeApiClient;
import org.clayman.safe.background.entity.OrderResult;
import org.clayman.safe.background.entity.Status;
import org.clayman.safe.background.repository.OrderResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class SafeCheckService {

    private static final Logger log = LoggerFactory.getLogger(SafeCheckService.class);

    @Autowired
    private SafeApiClient safeApiClient;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private OrderResultRepository orderResultRepository;

    public void handle(UUID orderUuid, String url) {
        log.info("Start handling order with uuid={}", orderUuid);
        Status status = cacheService.get(url);
        if (status == null) {
            log.info("Cache miss for url={}", url);
            status = safeApiClient.checkUrl(url);
            cacheService.put(url, status);
        } else {
            log.info("Cache hit for url={}", url);
        }
        OrderResult orderResult = new OrderResult();
        orderResult.setCheckDate(Instant.now());
        orderResult.setId(orderUuid);
        orderResult.setStatus(status);
        orderResult.setUrl(url);
        orderResultRepository.save(orderResult);
        log.info("Complete handling order with uuid={}", orderUuid);
    }
}
