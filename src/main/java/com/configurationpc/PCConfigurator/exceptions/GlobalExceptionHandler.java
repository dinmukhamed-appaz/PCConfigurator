package com.configurationpc.PCConfigurator.exceptions;

import com.configurationpc.PCConfigurator.dto.BuildErrorDto;
import com.configurationpc.PCConfigurator.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotIdFoundException.class)
    public ResponseEntity<ErrorResponseDto> componentIdNotFound(NotIdFoundException idException) {
        ErrorResponseDto error = new ErrorResponseDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                idException.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncompatibilityIssuesException.class)
    public ResponseEntity<BuildErrorDto> incompatibility(IncompatibilityIssuesException errorDto){
        BuildErrorDto error = new BuildErrorDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errorDto.getMessage(),
                errorDto.getIssues()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ComponentsNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> componendNotFound(ComponentsNotFoundException notFoundException){
        ErrorResponseDto error = new ErrorResponseDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                notFoundException.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(CategoryAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto> categoryAlreadyExist(CategoryAlreadyExistException categoryAlreadyExistException){
        ErrorResponseDto error = new ErrorResponseDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_GATEWAY.getReasonPhrase(),
                categoryAlreadyExistException.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY);
    }

}
