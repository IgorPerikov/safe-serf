package org.clayman.safe.api.repository;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import org.clayman.safe.api.entity.OrderResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import java.util.UUID;

@Repository
public class OrderResultRepository {

    private static final String TABLE_NAME = "order_results";

    @Autowired
    private Session session;

    @Autowired
    private MappingManager mappingManager;

    private Mapper<OrderResult> mapper;

    @PostConstruct
    private void init() {
        mapper = mappingManager.mapper(OrderResult.class);
    }

    @Nullable
    public OrderResult find(UUID id) {
        Statement statement = QueryBuilder
                .select()
                .from(TABLE_NAME)
                .where(QueryBuilder.eq("id", id));
        statement.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
        ResultSet resultSet = session.execute(statement);
        Result<OrderResult> mappedOrders = mapper.map(resultSet);
        return mappedOrders.one();
    }
}
