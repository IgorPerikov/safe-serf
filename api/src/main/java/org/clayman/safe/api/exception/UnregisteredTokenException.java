package org.clayman.safe.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Token is not registered")
public class UnregisteredTokenException extends RuntimeException {

}
