package org.clayman.safe.background.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.clayman.safe.background.api.CertlyApiClient;
import org.clayman.safe.background.api.SafeApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:token.properties")
public class ApiClientConfiguration {

    @Bean
    public SafeApiClient safeApiClient(@Value("${token}") String token, ObjectMapper objectMapper) {
        return new CertlyApiClient(token, objectMapper);
    }
}
