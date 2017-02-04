package org.clayman.safe.background.repository;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.clayman.safe.background.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Repository
public class OrderRepository {

    private static final String TABLE_NAME = "orders";
    private static final String ID_COLUMN_NAME = "id";
    private static final String IS_READY_COLUMN_NAME = "is_ready";

    @Autowired
    private Session session;

    @Autowired
    private MappingManager mappingManager;

    private Mapper<Order> mapper;

    @PostConstruct
    private void init() {
        mapper = mappingManager.mapper(Order.class);
    }

    public void setIsReady(UUID uuid) {
        Statement statement = QueryBuilder
                .update(TABLE_NAME)
                .with(QueryBuilder.set(IS_READY_COLUMN_NAME, true))
                .where(QueryBuilder.eq(ID_COLUMN_NAME, uuid));
        statement.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
        session.execute(statement);
    }
}