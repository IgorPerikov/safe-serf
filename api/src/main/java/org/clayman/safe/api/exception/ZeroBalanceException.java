package org.clayman.safe.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PAYMENT_REQUIRED, reason = "Token balance is zero, no more requests allowed")
public class ZeroBalanceException extends RuntimeException {

}
