package com.diegofma.countriesdata.apirest.exceptionhandler;

import org.openapitools.model.ErrorDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.validation.ValidationException;
import java.util.concurrent.TimeoutException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class, NoResultException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDTO> handleNotFoundException(RuntimeException ex) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setDetail("No data found with these parameters.");
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleValidationException(RuntimeException ex) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setDetail("Bad input data.");
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RuntimeException.class, Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDTO> handleRuntimeException(RuntimeException ex) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setDetail("Unhandled error.");
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorDTO> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setDetail("Request to database returned error.");
        return new ResponseEntity<>(errorDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TimeoutException.class)
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    public ResponseEntity<ErrorDTO> handleTimeoutException(TimeoutException ex) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setDetail("Error processing the request" + ex.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.GATEWAY_TIMEOUT);
    }
}
