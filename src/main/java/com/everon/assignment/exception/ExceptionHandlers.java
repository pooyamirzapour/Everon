package com.everon.assignment.exception;

import com.everon.assignment.model.msg.ErrorMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception Handler class for creating proper message and log.
 * @author Pooya Mirzapour (pooyamirzapour@gmail.com)
 */

@ControllerAdvice
@Slf4j
public class ExceptionHandlers {

    /**
     * Handle all exceptions with message, status, and log.
     * @param exception
     * @return ResponseEntity
     */
    @ExceptionHandler(ApiServiceException.class)
    public ResponseEntity<?> apiServiceException(ApiServiceException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(new ErrorMsg(exception.getErrorCode().getValue(), exception.getMessage()), exception.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> apiServiceException(Exception exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(new ErrorMsg(2000, exception.getMessage()==null?"Internal Error":exception.getMessage()),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
