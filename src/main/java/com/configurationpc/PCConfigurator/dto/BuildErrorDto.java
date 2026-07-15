package com.configurationpc.PCConfigurator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor

public class BuildErrorDto {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private List<String> details;
}
