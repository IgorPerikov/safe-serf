package org.clayman.safe.api.service;

import org.clayman.safe.api.entity.OrderResult;
import org.clayman.safe.api.repository.OrderResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.UUID;

@Service
public class OrderResultService {

    @Autowired
    private OrderResultRepository orderResultRepository;

    @Nullable
    public OrderResult getResult(UUID id) {
        return orderResultRepository.find(id);
    }
}
