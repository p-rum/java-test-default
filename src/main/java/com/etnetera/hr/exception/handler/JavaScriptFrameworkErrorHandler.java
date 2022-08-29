package com.etnetera.hr.exception.handler;


import com.etnetera.hr.exception.JavaScriptFrameworkAlreadyExistsException;
import com.etnetera.hr.exception.JavaScriptFrameworkNotFoundException;
import com.etnetera.hr.exception.JavaScriptFrameworkVersionAlreadyExistsException;
import com.etnetera.hr.exception.JavaScriptFrameworkVersionNotFoundException;
import com.etnetera.hr.model.response.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@RequiredArgsConstructor
public class JavaScriptFrameworkErrorHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value
            = {JavaScriptFrameworkVersionAlreadyExistsException.class, ConstraintViolationException.class, JavaScriptFrameworkAlreadyExistsException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, mapToErrorResponse("JavaScriptFramework already exists", ex, request), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value
            = {IllegalArgumentException.class, EnumConstantNotPresentException.class, NumberFormatException.class, MethodArgumentTypeMismatchException.class})
    protected ResponseEntity<Object> handleBadRequest(
            RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, mapToErrorResponse("Bad request", ex, request), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {JavaScriptFrameworkNotFoundException.class, JavaScriptFrameworkVersionNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, mapToErrorResponse("JavaScriptFramework not found", ex, request), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleDefault(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Unexpected error occurred.", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


    private ErrorResponse mapToErrorResponse(String title, Exception exception, WebRequest request) {

        return ErrorResponse.builder()
                            .errorMessage(ErrorResponse.ErrorMessage.builder()
                                                                    .description(exception.getMessage())
                                                                    .title(title)
                                                                    .build()).build();
    }


}
