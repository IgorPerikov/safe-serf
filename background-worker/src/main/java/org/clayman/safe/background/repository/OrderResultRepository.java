package org.clayman.safe.background.repository;

import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.clayman.safe.background.entity.OrderResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class OrderResultRepository {

    @Autowired
    private MappingManager mappingManager;

    private Mapper<OrderResult> mapper;

    @PostConstruct
    private void init() {
        mapper = mappingManager.mapper(OrderResult.class);
    }

    public void save(OrderResult orderResult) {
        mapper.save(orderResult);
    }
}
