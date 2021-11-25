package com.everon.assignment.exception;

import com.everon.assignment.model.enums.ErrorCode;
import org.springframework.http.HttpStatus;

/**
 * A customized exception.
 * @author Pooya Mirzapour (pooyamirzapour@gmail.com)
 */

public class ApiServiceException extends Exception   {

    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    /**
     * Build a new exception with the specified error code, detail message,status for log.
     * @param message
     * @param errorCode
     * @param httpStatus
     */
    public ApiServiceException(String message, ErrorCode errorCode, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;

    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
