package org.clayman.safe.api.web.controller;

import org.clayman.safe.api.domain.TokenDto;
import org.clayman.safe.api.service.TokenService;
import org.clayman.safe.api.utility.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// TODO: safer way than using req params
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/token/remember")
    public ResponseEntity<TokenDto> returnBalanceAndTokenByUserCredentials(
            @RequestParam String login,
            @RequestParam String password
    ) {
        return ResponseEntityBuilder.of(tokenService.returnTokenForUser(login, password));
    }

    @GetMapping("/token/balance")
    public ResponseEntity<TokenDto> returnBalanceByUserToken(@RequestParam String token) {
        return ResponseEntityBuilder.of(tokenService.returnTokenById(token));
    }

    @PostMapping("/token/register")
    public ResponseEntity<TokenDto> registerNewUser(@RequestParam String login, @RequestParam String password) {
        return ResponseEntityBuilder.of(tokenService.registerNewToken(login, password));
    }
}
