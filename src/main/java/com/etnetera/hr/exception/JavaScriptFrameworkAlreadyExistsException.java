package com.etnetera.hr.exception;

public class JavaScriptFrameworkAlreadyExistsException extends RuntimeException {
    private static final String MESSAGE = "JavaScriptFramework with this name already exists. Framework name: [%s]";

    public JavaScriptFrameworkAlreadyExistsException(String frameworkName) {
        super(String.format(MESSAGE, frameworkName));
    }
}
