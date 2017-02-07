package org.clayman.safe.api.service;

import org.clayman.safe.api.domain.TokenDto;
import org.clayman.safe.api.entity.Token;
import org.clayman.safe.api.exception.UserAlreadyExistedException;
import org.clayman.safe.api.repository.TokenRepository;
import org.clayman.safe.api.utility.HashGeneratorUtility;
import org.clayman.safe.api.utility.TokenTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {

    // TODO:
    private static final Integer DEFAULT_BALANCE = 100;

    @Autowired
    private HashGeneratorUtility hashUtility;

    @Autowired
    private TokenRepository tokenRepository;

    private TokenTransformer tokenTransformer = new TokenTransformer();

    public TokenDto returnTokenForUser(String login, String password) {
        Token token = tokenRepository.findByLoginAndPassword(login, hashUtility.generateHash(password));
        return tokenTransformer.createDtoFromEntity(token);
    }

    public TokenDto returnTokenById(String tokenId) {
        Token token = tokenRepository.findById(tokenId);
        return tokenTransformer.createDtoFromEntity(token);
    }

    public TokenDto registerNewToken(String login, String password) {
        Token byLogin = tokenRepository.findByLogin(login);
        if (byLogin != null) {
            throw new UserAlreadyExistedException();
        }
        String tokenId = UUID.randomUUID().toString();
        Token token = new Token(tokenId, login, hashUtility.generateHash(password), DEFAULT_BALANCE);
        tokenRepository.registerNewToken(token);
        return new TokenDto(token);
    }
}
