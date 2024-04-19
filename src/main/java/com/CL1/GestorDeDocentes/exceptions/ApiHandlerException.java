package com.CL1.GestorDeDocentes.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ApiHandlerException {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handlerResponseStatusException(
            ResponseStatusException e
    ) {
        ErrorResponse error = new ErrorResponse(
                e.getReason(),
                e.getStatusCode().toString(),
                e.getStatusCode().value()
        );

        return new ResponseEntity<>(error, e.getStatusCode());
    }
}
