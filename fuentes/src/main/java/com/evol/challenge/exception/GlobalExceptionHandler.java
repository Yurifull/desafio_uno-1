package com.evol.challenge.exception;

import com.evol.challenge.dto.GenericResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeParseException;

/**
 * Global exception handler, this class is used to handle all exceptions
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle MethodArgumentNotValidException
     * @param ex MethodArgumentNotValidException
     * @return ResponseEntity with error messages
     */
    @ExceptionHandler({MethodArgumentNotValidException.class , ResourceAlreadyExistsException.class})
    public ResponseEntity<GenericResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });

        GenericResponse<Object> response = new GenericResponse<>();
        response.setErrors(errors);
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Validation error");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle HttpMessageNotReadableException
     * @param ex HttpMessageNotReadableException
     * @return ResponseEntity with error messages
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<GenericResponse<Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String errorMessage = ex.getMessage();

        // Extract more specific message if cause is DateTimeParseException
        if (ex.getCause() instanceof InvalidFormatException) {
            Throwable cause = ex.getCause().getCause();
            if (cause instanceof DateTimeParseException) {
                errorMessage = cause.getMessage();
            }
        }

        GenericResponse<Object> response = new GenericResponse<>();
        response.setMessage("JSON parse error: " + errorMessage);
        response.setCode(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle ResourceNotFoundException
     * @param ex ResourceNotFoundException
     * @return ResponseEntity with error messages
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GenericResponse<Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        GenericResponse<Object> response = new GenericResponse<>();
        response.setMessage(ex.getMessage());
        response.setCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
