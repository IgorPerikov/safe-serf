package org.clayman.safe.api.configuration;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ProtocolOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

@Configuration
public class CassandraConfiguration {

    public static final int CASSANDRA_DEFAULT_PORT = 9042;

    @Bean(destroyMethod = "close")
    public Cluster cluster() {
        Cluster.Builder clusterBuilder = Cluster.builder()
                .addContactPointsWithPorts(new InetSocketAddress("localhost", CASSANDRA_DEFAULT_PORT))
                .withCompression(ProtocolOptions.Compression.LZ4);
        return clusterBuilder.build();
    }

    @Bean(destroyMethod = "close")
    public Session session(Cluster cluster) {
        return cluster.connect();
    }

    @Bean
    public MappingManager mappingManager(Session session) {
        return new MappingManager(session);
    }
}
