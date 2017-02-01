package org.clayman.safe.api.entity;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Table;
import org.clayman.safe.api.validation.Url;

import java.util.UUID;

@Table(name = "orders")
public class Order {

    @Column(name = "id")
    private UUID id;

    @Url
    @Column(name = "url")
    private String url;

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
}
