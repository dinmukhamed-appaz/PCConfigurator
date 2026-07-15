package com.configurationpc.PCConfigurator.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String resourceName ,int id) {
        super(String.format("%s with id %d not found",resourceName, id));
    }
}
