package org.clayman.safe.api.configuration;

import com.datastax.driver.core.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CassandraConfiguration {

    @Bean
    public Session session() {

    }
}
