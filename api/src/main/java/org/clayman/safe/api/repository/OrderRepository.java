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
import java.util.UUID;

@Repository
public class OrderRepository {

    private static final String TABLE_NAME = "orders";

    @Autowired
    private Session session;

    @Autowired
    private MappingManager mappingManager;

    private Mapper<Order> mapper;

    @PostConstruct
    private void init() {
        mapper = mappingManager.mapper(Order.class);
    }

    public Order find(UUID id) {
        Statement statement = QueryBuilder
                .select()
                .from(TABLE_NAME)
                .where(QueryBuilder.eq("id", id));
        statement.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
        ResultSet resultSet = session.execute(statement);
        Result<Order> mappedOrders = mapper.map(resultSet);
        return mappedOrders.one();
    }

    public void save(Order order) {
        mapper.save(order);
    }
}
