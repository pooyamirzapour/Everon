package com.everon.assignment.model.msg;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * Error message class.
 * @author Pooya Mirzapour (pooyamirzapour@gmail.com)
 */

@Getter
@Setter
public class ErrorMsg implements Serializable {
    private int errorCode;
    private String errorMessage;

    public ErrorMsg(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
