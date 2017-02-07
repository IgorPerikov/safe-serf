package org.clayman.safe.api.utility;

import org.clayman.safe.api.domain.TokenDto;
import org.clayman.safe.api.entity.Token;

public class TokenTransformer {

    public TokenDto createDtoFromEntity(Token token) {
        if (token == null) return null;
        return new TokenDto(token);
    }
}
