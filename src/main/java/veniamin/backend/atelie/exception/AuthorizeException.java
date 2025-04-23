package veniamin.backend.atelie.exception;

import lombok.Getter;
import veniamin.backend.atelie.exception.errors.AuthorizedError;

@Getter
public class AuthorizeException extends BusinessException {

    private String errorName;

    public AuthorizeException(AuthorizedError authorizedError) {
        super(authorizedError.getMessage());
        this.errorName = authorizedError.name();
    }

    public AuthorizeException(String message, String errorName) {
        super(message);
        this.errorName = errorName;
    }
}
