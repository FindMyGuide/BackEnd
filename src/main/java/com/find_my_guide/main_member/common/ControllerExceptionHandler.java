//package com.find_my_guide.main_member.common;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.NoHandlerFoundException;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.stream.Collectors;
//
//@ControllerAdvice
//public class ControllerExceptionHandler {
//
//    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);
//
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException e) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("찾을 수 없습니다.");
//    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
//        return ResponseEntity.badRequest().body(e.getMessage());
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGeneralException(Exception e) {
//        log.error("서버에서 오류 발생", e);
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류가 발생했습니다.");
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException e) {
//        String message = e.getBindingResult()
//                .getAllErrors().stream()
//                .map(ObjectError::getDefaultMessage)
//                .collect(Collectors.joining(", "));
//        return ResponseEntity.badRequest().body(message);
//    }
//
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
//        return ResponseEntity.badRequest().body("잘못된 요청 본문입니다.");
//    }
//
//    @ExceptionHandler(NoHandlerFoundException.class)
//    public ResponseEntity<String> handleNoHandlerFoundException(NoHandlerFoundException e) {
//        return ResponseEntity.notFound().build();
//    }
//
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public ResponseEntity<String> handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
//        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("지원하지 않는 HTTP 메서드입니다.");
//    }
//}
