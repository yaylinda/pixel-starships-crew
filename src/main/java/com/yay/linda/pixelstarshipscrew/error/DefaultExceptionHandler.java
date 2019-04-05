package com.yay.linda.pixelstarshipscrew.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> processUsernameNotFoundException(Exception ex) {
        ErrorDTO errorDTO = new ErrorDTO(ex.getMessage());
        LOGGER.error(ex.getMessage(), ex);
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RegisterException.class)
    public ResponseEntity<ErrorDTO> processRegisterException(Exception ex) {
        ErrorDTO errorDTO = new ErrorDTO(ex.getMessage());
        LOGGER.error(ex.getMessage(), ex);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernamePasswordMismatchException.class)
    public ResponseEntity<ErrorDTO> processUsernamePasswordMismatchException(Exception ex) {
        ErrorDTO errorDTO = new ErrorDTO(ex.getMessage());
        LOGGER.error(ex.getMessage(), ex);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> processRuntimeException(Exception ex) {
        ErrorDTO errorDTO = new ErrorDTO(ex.getMessage());
        LOGGER.error(ex.getMessage(), ex);
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}