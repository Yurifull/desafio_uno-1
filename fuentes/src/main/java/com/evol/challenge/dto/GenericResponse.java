package com.evol.challenge.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

/**
 * Generic response class, used to return a generic response to the client with data, errors, message and code.
 * @param <T> generic type, usually an Entity class or DTO class.
 */
@NoArgsConstructor
@Getter
@Setter
public class GenericResponse<T> {
    private T data;
    private List<String> errors;
    private String message;
    private Integer code;
}
