package com.upi.upiqrgenerator.rest;

import com.google.zxing.WriterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class UPIQrErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException() {
        return new ResponseEntity<>("One or more parameters is missing or incorrect.",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WriterException.class)
    public ResponseEntity<String> handleQrGenerationException() {
        return new ResponseEntity<>("An unknwon error occured while generating QR code.",HttpStatus.INTERNAL_SERVER_ERROR);
    }

}