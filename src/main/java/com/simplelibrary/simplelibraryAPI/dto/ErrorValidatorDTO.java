package com.simplelibrary.simplelibraryAPI.dto;
import org.springframework.validation.FieldError;
public record ErrorValidatorDTO(String field, String message){
    public ErrorValidatorDTO(FieldError error){
        this(error.getField(), error.getDefaultMessage());
    }
}