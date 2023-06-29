package com.evol.challenge.util;

public interface EntityConverter<E, D> {
    D convertToDto(E entity);
    E convertToEntity(D dto);
}