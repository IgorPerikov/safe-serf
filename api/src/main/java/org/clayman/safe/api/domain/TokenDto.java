package org.clayman.safe.api.domain;

import org.clayman.safe.api.entity.Token;

public class TokenDto {

    private String login;
    private String token;
    private Integer balance;

    public TokenDto() {}

    public TokenDto(Token token) {
        this.login = token.getLogin();
        this.token = token.getTokenId();
        this.balance = token.getBalance();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}