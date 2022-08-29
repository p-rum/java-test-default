package com.etnetera.hr.exception;

public class JavaScriptFrameworkNotFoundException extends RuntimeException {

    private static final String MESSAGE = "JavaScriptFramework with ID: [%s] not found.";

    public JavaScriptFrameworkNotFoundException(Long frameworkId) {
        super(String.format(MESSAGE, frameworkId));
    }
}
