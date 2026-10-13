package com.co.bankInc.exceptions;

import com.co.bankInc.exceptions.impl.ResourceBadRequestException;
import com.co.bankInc.exceptions.impl.ResourceNotFoundException;
import com.co.bankInc.model.generalMessage.MessageDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalControllerExceptionHandlerTest {

    private final GlobalControllerExceptionHandler exceptionHandler = new GlobalControllerExceptionHandler();

    @Test
    void testHandleException() {

        Exception exception = new Exception("Test exception");

        ResponseEntity<MessageDTO> responseEntity = exceptionHandler.handleException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getBody().getCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getBody().getMessage());
    }

    @Test
    void testHandleResourceNotFoundException() {

        ResourceNotFoundException exception = new ResourceNotFoundException("Resource not found");


        ResponseEntity<MessageDTO> responseEntity = exceptionHandler.handleResourceNotFoundException(exception);


        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getBody().getCode());
        assertEquals("Resource not found", responseEntity.getBody().getMessage());
    }

    @Test
    void testHandleResourceBadRequestException() {

        ResourceBadRequestException exception = new ResourceBadRequestException("Bad request");

        ResponseEntity<MessageDTO> responseEntity = exceptionHandler.handleConversion(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getBody().getCode());
        assertEquals("Bad request", responseEntity.getBody().getMessage());
    }

    @Test
    void testHandleNoResourceFoundException() {

        NoResourceFoundException exception = new NoResourceFoundException(HttpMethod.GET, "/h2-console");

        ResponseEntity<MessageDTO> responseEntity = exceptionHandler.handleException(exception);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getBody().getCode());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), responseEntity.getBody().getMessage());
    }

    @Test
    void testHandleMethodNotSupportedException() {

        HttpRequestMethodNotSupportedException exception = new HttpRequestMethodNotSupportedException("DELETE");

        ResponseEntity<MessageDTO> responseEntity = exceptionHandler.handleException(exception);

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, responseEntity.getStatusCode());
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), responseEntity.getBody().getCode());
    }

    @Test
    void testHandleTypeMismatchException() {

        TypeMismatchException exception = new TypeMismatchException("abc", Long.class);

        ResponseEntity<MessageDTO> responseEntity = exceptionHandler.handleException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getBody().getCode());
    }
}
