package com.safetynets.alerts.exception;

import com.safetynets.alerts.exception.NoFirestationFound;

import com.safetynets.alerts.model.Error;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    private static final Logger logger = LogManager.getLogger(ControllerAdvisor.class);

    @ExceptionHandler(NoFirestationFound.class)
    @ResponseBody
    public ResponseEntity<?> handleNoFirestationFoundException(NoFirestationFound e) {
        logger.error("Firestation not found!");
        return response(new Error(404, "FIRESTATION_NOT_FOUND",
                e.getMessage()));
    }
    
    protected ResponseEntity<Error> response(Error error) {
        HttpStatus status = HttpStatus.resolve(error.getStatus());
        logger.debug("Responding with a status of {}", status);
        return new ResponseEntity<>(error, new HttpHeaders(), status);
    }
    
}