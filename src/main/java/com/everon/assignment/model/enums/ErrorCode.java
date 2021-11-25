package com.everon.assignment.model.enums;

/**
 * Enum for all possible error codes.
 * @author Pooya Mirzapour (pooyamirzapour@gmail.com)
 */
public enum ErrorCode {

   ID_IS_NOT_VALID("Id is not valid",1001);

    private int value;
    private String message;

     ErrorCode(String message ,int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }
}