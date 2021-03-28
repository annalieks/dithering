package com.processor.dithering.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.MessageFormat;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ProcessingException.class)
    protected ResponseEntity<Object> handleProcessingException(ProcessingException e) {
        return new ResponseEntity<>(
                MessageFormat.format("Could not process the image: {0}", e.getMessage()),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException e) {
        return new ResponseEntity<>(
                MessageFormat.format("Could not find the image: {0}", e.getMessage()),
                new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}
