package com.example.vrcapp.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiException extends RuntimeException {
    private long version = 1L;
    private String message;

    public ApiException(long version, String message) {
        super(message);
        this.version = version;
        this.message = message;
    }
}
