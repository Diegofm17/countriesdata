package com.diegofma.countriesdata.apirest.exceptionhandler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.model.ErrorDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.util.concurrent.TimeoutException;

public class ControllerExceptionHandlerTest {

    @InjectMocks
    private ControllerExceptionHandler controllerExceptionHandler;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void handleNotFoundException_returnsNotFound() {
        EntityNotFoundException ex = new EntityNotFoundException();
        ResponseEntity<ErrorDTO> responseEntity = controllerExceptionHandler.handleNotFoundException(ex);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assertions.assertEquals("No data found with these parameters.", responseEntity.getBody().getDetail());
    }

    @Test
    public void handleValidationException_returnsBadRequest() {
        ValidationException ex = new ValidationException();
        ResponseEntity<ErrorDTO> responseEntity = controllerExceptionHandler.handleValidationException(ex);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Assertions.assertEquals("Bad input data.", responseEntity.getBody().getDetail());
    }

    @Test
    public void handleRuntimeException_returnsInternalServerError() {
        RuntimeException ex = new RuntimeException();
        ResponseEntity<ErrorDTO> responseEntity = controllerExceptionHandler.handleRuntimeException(ex);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        Assertions.assertEquals("Unhandled error.", responseEntity.getBody().getDetail());
    }

    @Test
    public void handleDataIntegrityViolationException_returnsConflict() {
        DataIntegrityViolationException ex = new DataIntegrityViolationException("");
        ResponseEntity<ErrorDTO> responseEntity = controllerExceptionHandler.handleDataIntegrityViolationException(ex);
        Assertions.assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        Assertions.assertEquals("Request to database returned error.", responseEntity.getBody().getDetail());
    }

    @Test
    public void handleTimeoutException_returnsGatewayTimeout() {
        TimeoutException ex = new TimeoutException();
        ResponseEntity<ErrorDTO> responseEntity = controllerExceptionHandler.handleTimeoutException(ex);
        Assertions.assertEquals(HttpStatus.GATEWAY_TIMEOUT, responseEntity.getStatusCode());
        Assertions.assertEquals("Error processing the request" + ex.getMessage(), responseEntity.getBody().getDetail());
    }
}
