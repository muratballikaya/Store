package com.getir.rig.store.handler;

import com.getir.rig.store.exception.NoSuchBookException;
import com.getir.rig.store.exception.NotEnoughBookException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;


@ControllerAdvice
public class BookExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(BookExceptionHandler.class);

    @ExceptionHandler(value = {NotEnoughBookException.class})
    public ResponseEntity<Object> handleNotEnoughBookException(NotEnoughBookException ex){
        logger.error("There are not enough books : ",ex.getMessage());
        return new ResponseEntity<Object>( "There are not enough books : " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NoSuchBookException.class})
    public ResponseEntity<Object> handleNoSuchBookException(NoSuchBookException ex){
        logger.error("There is no such book : ",ex.getMessage());
        return new ResponseEntity<Object>("There is no such book : " +ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<Object> handleValidationException(NotEnoughBookException ex){
        logger.error("Validation exception occured : ",ex.getMessage());
        return new ResponseEntity<Object>("Validation exception occured : " +ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        logger.error("Method argument is not valid : ",ex.getMessage());
        return new ResponseEntity<Object>("Method argument is not valid : " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
