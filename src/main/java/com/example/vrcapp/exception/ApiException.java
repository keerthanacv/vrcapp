package com.example.vrcapp.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
public class ApiException extends RuntimeException {
    private long version = 1L;
    private String message;

    private HttpStatusCode statusCode;

    public ApiException(long version, String message,HttpStatusCode statusCode) {
        super(message);
        this.version = version;
        this.message = message;
        this.statusCode = statusCode;
    }
}
