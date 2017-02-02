package org.clayman.safe.background.service;

import com.github.benmanes.caffeine.cache.Cache;
import org.clayman.safe.background.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Service
public class CacheService {

    @Autowired
    private Cache<String, Status> cache;

    public void put(@Nonnull String url, @Nonnull Status status) {
        cache.put(url, status);
    }

    @Nullable
    public Status get(String url) {
        return cache.getIfPresent(url);
    }
}
