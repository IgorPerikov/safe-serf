package org.clayman.safe.api.entity;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Table;

import java.time.Instant;
import java.util.UUID;

@Table(name = "order_results")
public class OrderResult {

    @Column(name = "id")
    private UUID id;

    @Column(name = "url")
    private String url;

    @Column(name = "check_date")
    private Instant checkDate;

    @Column(name = "status")
    private Status status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Instant getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Instant checkDate) {
        this.checkDate = checkDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
