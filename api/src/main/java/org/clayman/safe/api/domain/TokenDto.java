package org.clayman.safe.api.domain;

import org.clayman.safe.api.entity.Token;

public class TokenDto {

    private String login;
    private String token;
    private Integer maximumBalance;
    private Integer currentBalance;

    public TokenDto() {}

    public TokenDto(Token token) {
        this.login = token.getLogin();
        this.token = token.getTokenId();
        this.maximumBalance = token.getMaximumBalance();
        this.currentBalance = token.getCurrentBalance();
    }

    public TokenDto(String login, String token, Integer maximumBalance, Integer currentBalance) {
        this.login = login;
        this.token = token;
        this.maximumBalance = maximumBalance;
        this.currentBalance = currentBalance;
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
