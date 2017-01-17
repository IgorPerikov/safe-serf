package org.clayman.safe.api.service;

import org.clayman.safe.api.repository.OrderResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderResultService {

    @Autowired
    private OrderResultRepository orderResultRepository;
}
