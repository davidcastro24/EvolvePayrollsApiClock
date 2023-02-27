package com.davcode.clock.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ClockExceptionHandler {

    @ExceptionHandler(Exceptions.class)
    public ResponseEntity<String> handleExceptions(Exceptions exceptions){
        HttpStatus status = getStatus(exceptions);
        return ResponseEntity.status(status).body(exceptions.getMessage());
    }

    private HttpStatus getStatus(Exceptions exceptions){
        if (exceptions instanceof Exceptions.ClockNotFoundException
        || exceptions instanceof Exceptions.EmployeeNotFoundException
        || exceptions instanceof Exceptions.UserNotFoundException){
            return HttpStatus.NOT_FOUND;
        }else if(exceptions instanceof  Exceptions.InvalidDataException){
            return HttpStatus.BAD_REQUEST;
        }else if(exceptions instanceof Exceptions.UnauthorizedException){
            return HttpStatus.UNAUTHORIZED;
        }else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}
