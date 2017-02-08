package org.clayman.safe.api.service.scheduled;

import org.clayman.safe.api.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class UserBalanceRefresher {

    @Autowired
    private TokenRepository tokenRepository;

    @Scheduled(cron = "0 0 0 * * *")
    private void renew() {
        tokenRepository.refreshAllBalances();
    }
}
