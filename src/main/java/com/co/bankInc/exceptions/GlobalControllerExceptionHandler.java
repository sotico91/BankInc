package com.co.bankInc.exceptions;

import com.co.bankInc.exceptions.impl.ResourceBadRequestException;
import com.co.bankInc.exceptions.impl.ResourceNotFoundException;
import com.co.bankInc.model.generalMessage.MessageDTO;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageDTO> handleException(Exception ex) {

        HttpStatus status = resolveStatus(ex);

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setCode(status.value());
        messageDTO.setMessage(status.getReasonPhrase());

        return ResponseEntity.status(status).body(messageDTO);
    }

    // Spring's own request errors (404, 405, 415, missing parameters...) carry their status;
    // anything else is an unexpected server error.
    private HttpStatus resolveStatus(Exception ex) {
        if (ex instanceof ErrorResponse errorResponse) {
            HttpStatus status = HttpStatus.resolve(errorResponse.getStatusCode().value());
            return status != null ? status : HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (ex instanceof TypeMismatchException || ex instanceof HttpMessageNotReadableException) {
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<MessageDTO> handleResourceNotFoundException(ResourceNotFoundException ex) {

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setCode(HttpStatus.NOT_FOUND.value());
        messageDTO.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageDTO);
    }

    @ExceptionHandler(ResourceBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<MessageDTO> handleConversion(RuntimeException ex) {

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setCode(HttpStatus.BAD_REQUEST.value());
        messageDTO.setMessage(ex.getMessage());

        return new ResponseEntity<>(messageDTO, HttpStatus.BAD_REQUEST);
    }
}
