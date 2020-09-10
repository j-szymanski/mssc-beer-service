package prv.jws.microservices.beerservice.web.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> validationErrorHandler(MethodArgumentNotValidException ex) {
        log.info("Constraint Violation Errors!!!");
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                log.info("Property: {}; Message: {}", fieldError.getField(),
                        fieldError.getDefaultMessage()));
        List<String> errors = new ArrayList<>(ex.getBindingResult().getErrorCount());
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.add(fieldError.getField()+" : "+fieldError.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<ObjectError>> handleBindException(BindException ex){
        return new ResponseEntity<>(ex.getAllErrors(), HttpStatus.BAD_REQUEST);

    }
}
