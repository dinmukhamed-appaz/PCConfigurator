package com.configurationpc.PCConfigurator.exceptions;

public class NotIdFoundException extends RuntimeException {
    public NotIdFoundException(String resourceName , int id) {
        super(String.format("%s with id %d not found",resourceName, id));
    }
}
