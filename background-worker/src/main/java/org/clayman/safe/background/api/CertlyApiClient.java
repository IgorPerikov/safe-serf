package org.clayman.safe.background.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.clayman.safe.background.entity.Status;

import java.io.IOException;
import java.util.List;

public class CertlyApiClient implements SafeApiClient {

    private String apiToken;
    private ObjectMapper objectMapper;

    public CertlyApiClient(String apiToken, ObjectMapper objectMapper) {
        this.apiToken = apiToken;
        this.objectMapper = objectMapper;
    }

    private String template = "https://api.certly.io/v1/lookup?url={url}&token={token}";

    @Override
    // TODO: fill hystrix command props
//    @HystrixCommand()
    public Status checkUrl(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(template
                        .replace("{url}", url)
                        .replace("{token}", apiToken))
                .build();

        String responseBody = client.newCall(request).execute().body().string();
        ResultDto resultDto = objectMapper.readValue(responseBody, ResultDto.class);

        return resultDto.getData().get(0).getStatus();
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