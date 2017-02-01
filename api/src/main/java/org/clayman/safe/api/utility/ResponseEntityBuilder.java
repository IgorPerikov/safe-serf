package org.clayman.safe.api.utility;

import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {

    public static <T> ResponseEntity<T> of(T entity) {
        if (entity == null) {
            return notFoundResponse();
        } else {
            return okResponse(entity);
        }
    }

    private static <T> ResponseEntity<T> notFoundResponse() {
        return ResponseEntity.notFound().build();
    }

    private static <T> ResponseEntity<T> okResponse(T entity) {
        return ResponseEntity.ok(entity);
    }
}
