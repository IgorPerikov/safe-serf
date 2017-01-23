package org.clayman.safe.background.api;

import org.clayman.safe.background.entity.Status;

public interface SafeApiClient {

    Status checkUrl(String url);
}
