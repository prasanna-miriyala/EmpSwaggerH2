
package com.example.employee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, Object>> handleBadReq(BadRequestException ex){
        Map<String,Object> body=new HashMap<>();
        body.put("status",HttpStatus.BAD_REQUEST.value());
        body.put("error","NotFound");
        body.put("message",ex.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>>  handleResourceNotFound(ResourceNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not found");
        body.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleValidation(MethodArgumentNotValidException ex){
        List<String>  details=ex.getBindingResult().getFieldErrors().stream().
                map(err->err.getField() +":"+err.getDefaultMessage()).collect(Collectors.toList());
        Map<String,Object> body=new HashMap<>();
        body.put("status",HttpStatus.BAD_REQUEST.value());
        body.put("error","Validation failed");
        body.put("details",details);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    }





