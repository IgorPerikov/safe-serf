package org.clayman.safe.background.api;

import org.clayman.safe.background.entity.Status;

import java.io.IOException;

public interface SafeApiClient {

    Status checkUrl(String url) throws IOException;
}
