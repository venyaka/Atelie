package veniamin.backend.atelie.exception;

import lombok.Getter;
import veniamin.backend.atelie.exception.errors.NotFoundError;

@Getter
public class NotFoundException extends BusinessException {

    private String errorName;

    public NotFoundException(NotFoundError notFoundError) {
        super(notFoundError.getMessage());
        errorName = notFoundError.name();
    }

    public NotFoundException(String message, String errorName) {
        super(message);
        this.errorName = errorName;
    }
}
