package com.etnetera.hr.exception;

public class JavaScriptFrameworkVersionNotFoundException extends RuntimeException {

    private static final String MESSAGE = "JavaScriptFramework version with ID: [%s] not found.";

    public JavaScriptFrameworkVersionNotFoundException(Long id) {
        super(String.format(MESSAGE, id));
    }
}
