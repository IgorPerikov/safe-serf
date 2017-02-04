package org.clayman.safe.background.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.clayman.safe.background.entity.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CertlyApiClient implements SafeApiClient {

    private static final Logger log = LoggerFactory.getLogger(CertlyApiClient.class);

    private String apiToken;
    private ObjectMapper objectMapper;

    public CertlyApiClient(String apiToken, ObjectMapper objectMapper) {
        this.apiToken = apiToken;
        this.objectMapper = objectMapper;
    }

    private String template = "https://api.certly.io/v1/lookup?url={url}&token={token}";

    @Override
    public Status checkUrl(String url) {
        log.info("Starting check for url={}", url);
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(1500, TimeUnit.MILLISECONDS)
                .connectTimeout(250, TimeUnit.MILLISECONDS)
                .build();
        Request request = new Request.Builder()
                .url(template
                        .replace("{url}", url)
                        .replace("{token}", apiToken))
                .build();

        ResultDto resultDto;

        try {
            String responseBody = client.newCall(request).execute().body().string();
            resultDto = objectMapper.readValue(responseBody, ResultDto.class);
        } catch (IOException ioe) {
            log.info("Check result failed, exception message is={}", ioe.getMessage());
            return Status.UNKNOWN;
        }

        Status status = resultDto.getData().get(0).getStatus();

        log.info("Completed check for url={}, result={}", url, status.toString());

        return status;
    }
}

class ResultDto {

    private List<StatusDto> data;

    public List<StatusDto> getData() {
        return data;
    }

    public void setData(List<StatusDto> data) {
        this.data = data;
    }
}

class StatusDto {

    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}