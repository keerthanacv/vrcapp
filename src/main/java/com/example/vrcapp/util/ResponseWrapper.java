package com.example.vrcapp.util;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseWrapper {
    private int status;
    private String message;
    private List<?> data;

    private List<?> errors;

    public ResponseWrapper(int status, String message, List<?> data,List<?> errors) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }
}
