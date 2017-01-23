package org.clayman.safe.background.configuration;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ProtocolOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.extras.codecs.enums.EnumNameCodec;
import com.datastax.driver.extras.codecs.jdk8.InstantCodec;
import com.datastax.driver.mapping.MappingManager;
import org.clayman.safe.background.entity.Status;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

@Configuration
public class CassandraConfiguration {

    private static final int CASSANDRA_DEFAULT_PORT = 9042;
    private static final String ORDER_KEYSPACE = "orders";

    @Bean(destroyMethod = "close")
    public Cluster cluster() {
        Cluster.Builder clusterBuilder = Cluster.builder()
                .addContactPointsWithPorts(new InetSocketAddress("localhost", CASSANDRA_DEFAULT_PORT))
                .withCompression(ProtocolOptions.Compression.LZ4);
        Cluster cluster = clusterBuilder.build();
        cluster.getConfiguration().getCodecRegistry()
                // TODO: split common stuff to separate module
                .register(new EnumNameCodec<>(Status.class))
                .register(InstantCodec.instance);
        return cluster;
    }

    @Bean(destroyMethod = "close")
    public Session session(Cluster cluster) {
        return cluster.connect(ORDER_KEYSPACE);
    }

    @Bean
    public MappingManager mappingManager(Session session) {
        return new MappingManager(session);
    }
}

