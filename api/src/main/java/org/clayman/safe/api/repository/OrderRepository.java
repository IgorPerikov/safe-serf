package org.clayman.safe.api.repository;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import org.clayman.safe.api.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Repository
public class OrderRepository {

    private static final String TABLE_NAME = "orders";
    private static final String CREATED_AT_COLUMN_NAME = "created_at";
    private static final String IS_READY_COLUMN_NAME = "is_ready";

    private static final Integer OUTDATE_MINUTES_COUNT = 10;
    private static final Integer OUTDATE_TASKS_LIMIT = 20;

    @Autowired
    private Session session;

    @Autowired
    private MappingManager mappingManager;

    private Mapper<Order> mapper;

    @PostConstruct
    private void init() {
        mapper = mappingManager.mapper(Order.class);
    }

    public void save(Order order) {
        mapper.save(order);
    }

    public List<Order> findOutdatedOrders() {
        Instant now = Instant.now();
        Statement statement = QueryBuilder
                .select()
                .from(TABLE_NAME)
                .allowFiltering()
                .where(QueryBuilder.lt(CREATED_AT_COLUMN_NAME, now.minus(OUTDATE_MINUTES_COUNT, ChronoUnit.MINUTES)))
                .and(QueryBuilder.eq(IS_READY_COLUMN_NAME, false))
                .limit(OUTDATE_TASKS_LIMIT);
        statement.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
        ResultSet resultSet = session.execute(statement);
        Result<Order> mappedOrders = mapper.map(resultSet);
        return mappedOrders.all();
    }
}
