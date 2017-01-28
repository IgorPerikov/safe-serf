package org.clayman.safe.background.service;

import org.clayman.safe.background.entity.Status;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Service
public class CacheService {

    public boolean contains(@Nonnull String url) {
        // TODO: stub
        return false;
    }

    public void put(@Nonnull String url, @Nonnull Status status) {
        // TODO: stub
    }

    @Nullable
    public Status get(String url) {
        throw new UnsupportedOperationException();
    }
}
