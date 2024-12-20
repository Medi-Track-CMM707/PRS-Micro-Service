package com.meditrack.prs.exception;


import com.meditrack.prs.constant.ErrorCode;
import com.meditrack.prs.model.dto.ErrorResponseDTO;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class PrsExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponseDTO> handlePatientInternalServerException(Exception e) {
        log.error("An error occurred in Exception", e);
        return new ResponseEntity<>(new ErrorResponseDTO(
                ErrorCode.PRS_002001.name(),
                ErrorCode.PRS_002001.getMessage(),
                e.getMessage()
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({PrsNotFoundException.class})
    public ResponseEntity<ErrorResponseDTO> handlePrsNotFoundException(PrsNotFoundException e) {
        log.error("Patient not found", e);
        return new ResponseEntity<>(new ErrorResponseDTO(
                e.getErrorCode().name(),
                e.getErrorCode().getMessage(),
                e.getMessage()
        ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({PrsInvalidRequestException.class})
    public ResponseEntity<ErrorResponseDTO> handlePrsInvalidRequestException(PrsInvalidRequestException e) {
        log.error("Invalid request", e);
        return new ResponseEntity<>(new ErrorResponseDTO(
                e.getErrorCode().name(),
                e.getErrorCode().getMessage(),
                e.getMessage()
        ), HttpStatus.BAD_REQUEST);
    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, @Nullable HttpHeaders headers, @Nullable HttpStatusCode status, @Nullable WebRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(new ErrorResponseDTO(
                ErrorCode.PRS_003001.name(),
                ErrorCode.PRS_003001.getMessage(),
                errors
        ), HttpStatus.BAD_REQUEST);
    }
}
