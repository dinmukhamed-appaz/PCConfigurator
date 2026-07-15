package com.configurationpc.PCConfigurator.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class IncompatibilityIssuesException extends RuntimeException {

    private final List<String> issues;

    public IncompatibilityIssuesException(List<String> issues) {
        super(String.format("Incompatibility issues: %s", issues));
        this.issues = issues;

    }

}
