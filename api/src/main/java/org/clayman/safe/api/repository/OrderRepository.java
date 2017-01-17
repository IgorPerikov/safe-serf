package org.clayman.safe.api.repository;

import com.datastax.driver.core.Session;
import org.clayman.safe.api.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class OrderRepository {

    @Autowired
    private Session session;

    public Order find(UUID id) {

    }

    public Order save(Order order) {

    }
}
