package org.clayman.safe.api.service;

import org.clayman.safe.api.domain.TokenDto;
import org.clayman.safe.api.entity.Token;
import org.clayman.safe.api.repository.TokenRepository;
import org.clayman.safe.api.utility.HashGeneratorUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private HashGeneratorUtility hashUtility;

    @Autowired
    private TokenRepository tokenRepository;

    public TokenDto returnTokenForUser(String login, String password) {
        Token token = tokenRepository.findByLoginAndPassword(login, hashUtility.generateHash(password));
        return new TokenDto(token);
    }

    public TokenDto returnTokenById(String tokenId) {
        Token token = tokenRepository.findById(tokenId);
        return new TokenDto(token);
    }

    public TokenDto registerNewToken(String login, String password) {
        Token token = tokenRepository.registerNewToken(login, hashUtility.generateHash(password));
        return new TokenDto(token);
    }
}
