package com.find_my_guide.main_member.common;

import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        log.error("handleValidationException", e);

        return bindingResult.getAllErrors()
            .stream()
            .map(error -> new StringBuilder().append("ObjectName=").append(error.getObjectName())
                .append(",Message=").append(error.getDefaultMessage())
                .append(",code=").append(error.getCode()))
            .collect(Collectors.joining(" | "));
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateException(DuplicateException e) {
        log.error("handleDuplicateException", e);
        ErrorResponse response = new ErrorResponse(e.getErrorCode());

        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        log.error("handleNotFoundException", e);
        ErrorResponse response = new ErrorResponse(e.getErrorCode());

        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("handleException", e);
        ErrorResponse response = new ErrorResponse(ErrorCode.INTER_SERVER_ERROR);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
