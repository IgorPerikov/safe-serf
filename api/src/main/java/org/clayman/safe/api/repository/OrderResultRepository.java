package org.clayman.safe.api.repository;

import com.datastax.driver.core.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderResultRepository {

    @Autowired
    private Session session;
}
