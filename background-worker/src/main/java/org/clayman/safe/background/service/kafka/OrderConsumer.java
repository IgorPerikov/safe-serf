package org.clayman.safe.background.service.kafka;

import org.clayman.safe.background.service.SafeCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @Autowired
    private SafeCheckService safeCheckService;


}
