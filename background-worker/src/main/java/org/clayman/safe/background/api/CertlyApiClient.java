package org.clayman.safe.background.api;

import org.clayman.safe.background.entity.Status;

public class CertlyApiClient implements SafeApiClient {

    private static final String TEMPLATE = "https://api.certly.io/v1/lookup?url=${url}&token=${token}";

    private String apiToken;

    public CertlyApiClient(String apiToken) {
        this.apiToken = apiToken;
    }

    @Override
    public Status checkUrl(String url) {

    }
}
