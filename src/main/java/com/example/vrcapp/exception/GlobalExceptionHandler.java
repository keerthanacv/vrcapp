package com.example.vrcapp.exception;
import com.example.vrcapp.exception.ApiException;
import com.example.vrcapp.util.ResponseWrapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@ComponentScan
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ResponseWrapper> handleApiException(ApiException ex) {
        return buildResponseEntity(new ResponseWrapper(ex.getStatus(), ex.getMessage(), null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ResponseWrapper> handleValidationExceptions(MethodArgumentNotValidException ex) {
        System.out.println("enter exception");
        BindingResult result = ex.getBindingResult();
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : result.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ResponseWrapper responseWrapper = new ResponseWrapper(HttpStatus.BAD_REQUEST.value(), "Validation error", errors);
        return buildResponseEntity(responseWrapper);
    }

    private ResponseEntity<ResponseWrapper> buildResponseEntity(ResponseWrapper responseWrapper) {
        return new ResponseEntity<>(responseWrapper, HttpStatus.valueOf(responseWrapper.getStatus()));
    }
}
