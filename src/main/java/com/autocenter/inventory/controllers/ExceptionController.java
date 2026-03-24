package com.autocenter.inventory.controllers;

import com.autocenter.inventory.dto.ResponseDTO;
import com.autocenter.inventory.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(exception = ResourceNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleMissingResourceException(ResourceNotFoundException resourceNotFoundException){
        ResponseDTO responseDTO= new ResponseDTO(404, resourceNotFoundException.getMessage());
        return ResponseEntity.badRequest().body(responseDTO);
    }
}
