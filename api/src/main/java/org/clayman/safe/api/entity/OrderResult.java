package org.clayman.safe.api.entity;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Table;

import java.util.UUID;

@Table(name = "order_results")
public class OrderResult {

    @Column(name = "id")
    private UUID id;

    @Column(name = "url")
    private String url;
}
