package org.clayman.safe.api.repository;

import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.clayman.safe.api.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class TokenRepository {

    @Autowired
    private Session session;

    @Autowired
    private MappingManager mappingManager;

    private Mapper<Token> mapper;

    @PostConstruct
    private void init() {
        mapper = mappingManager.mapper(Token.class);
    }

    public Token findByLoginAndPassword(String login, String hashedPassword) {
        // todo: allow filtering
    }

    public Token findById(String tokenId) {

    }

    public Token registerNewToken(String login, String hashedPassword) {
        // todo: find if exists before creating new one
    }
}
