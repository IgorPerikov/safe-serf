package org.clayman.safe.api.repository;

import com.datastax.driver.core.*;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import org.clayman.safe.api.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Executors;

@Repository
public class TokenRepository {

    private static final String TABLE_NAME = "tokens";

    @Autowired
    private Session session;

    @Autowired
    private MappingManager mappingManager;

    private Mapper<Token> mapper;

    @PostConstruct
    private void init() {
        mapper = mappingManager.mapper(Token.class);
    }

    @Nullable
    public Token findByLoginAndPassword(String login, String hashedPassword) {
        Statement statement = QueryBuilder
                .select()
                .from(TABLE_NAME)
                .allowFiltering()
                .where(QueryBuilder.eq("login", login))
                .and(QueryBuilder.eq("hashed_password", hashedPassword));
        statement.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
        ResultSet resultSet = session.execute(statement);
        Result<Token> mappedOrders = mapper.map(resultSet);
        return mappedOrders.one();
    }

    @Nullable
    public Token findByLogin(String login) {
        Statement statement = QueryBuilder
                .select()
                .from(TABLE_NAME)
                .allowFiltering()
                .where(QueryBuilder.eq("login", login));
        statement.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
        ResultSet resultSet = session.execute(statement);
        Result<Token> mappedOrders = mapper.map(resultSet);
        return mappedOrders.one();
    }

    @Nullable
    public Token findById(String tokenId) {
        Statement statement = QueryBuilder
                .select()
                .from(TABLE_NAME)
                .where(QueryBuilder.eq("token_id", tokenId));
        statement.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
        ResultSet resultSet = session.execute(statement);
        Result<Token> mappedOrders = mapper.map(resultSet);
        return mappedOrders.one();
    }

    public void registerNewToken(Token token) {
        mapper.save(token);
    }

    // TODO: async + move to background worker?
    public void refreshAllBalances() {
        List<Token> tokens = mapper.map(session.execute("Select * from orders.tokens")).all();
        for (Token token : tokens) {
            Statement statement = QueryBuilder
                    .update(TABLE_NAME)
                    .where(QueryBuilder.eq("token_id", token.getTokenId()))
                    .with(QueryBuilder.set("current_balance", token.getMaximumBalance()));
            session.execute(statement);
        }
    }
}
