package com.productservices.productservices.Controller;

import com.productservices.productservices.exceptions.NotFoundException;
import com.productservices.productservices.dtos.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(Exception exception){
        ErrorResponseDto errorResponseDto=new ErrorResponseDto();
        errorResponseDto.setErrorMessage(exception.getMessage());
        return  new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);

    }
}
