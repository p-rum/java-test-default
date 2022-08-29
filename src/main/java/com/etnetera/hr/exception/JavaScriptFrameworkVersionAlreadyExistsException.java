package com.etnetera.hr.exception;

public class JavaScriptFrameworkVersionAlreadyExistsException extends RuntimeException {
    private static final String MESSAGE = "JavaScriptFramework version already exists. Framework ID: [%s], version: [%s]";

    public JavaScriptFrameworkVersionAlreadyExistsException(Long frameworkId, String version) {
        super(String.format(MESSAGE, frameworkId, version));
    }
}
