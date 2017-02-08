package org.clayman.safe.api.entity;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Table;

@Table(name = "tokens")
public class Token {

    @Column(name = "token_id")
    private String tokenId;

    @Column(name = "login")
    private String login;

    @Column(name = "hashed_password")
    private String hashedPassword;

    @Column(name = "maximum_balance")
    private Integer maximumBalance;

    @Column(name = "current_balance")
    private Integer currentBalance;

    public Token() {}

    public Token(String tokenId, String login, String hashedPassword, Integer maximumBalance, Integer currentBalance) {
        this.tokenId = tokenId;
        this.login = login;
        this.hashedPassword = hashedPassword;
        this.maximumBalance = maximumBalance;
        this.currentBalance = currentBalance;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public Integer getMaximumBalance() {
        return maximumBalance;
    }

    public void setMaximumBalance(Integer maximumBalance) {
        this.maximumBalance = maximumBalance;
    }

    public Integer getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Integer currentBalance) {
        this.currentBalance = currentBalance;
    }
}
