package com.configurationpc.PCConfigurator.exceptions;

import com.configurationpc.PCConfigurator.dto.BuildErrorDto;
import com.configurationpc.PCConfigurator.dto.ErrorResponseDto;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> componentNotFound(NotFoundException ex){
        ErrorResponseDto error = new ErrorResponseDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncompatibilityIssuesException.class)
    public ResponseEntity<BuildErrorDto> incompatibility(IncompatibilityIssuesException errorDto){
        BuildErrorDto error = new BuildErrorDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                errorDto.getMessage(),
                errorDto.getIssues()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
