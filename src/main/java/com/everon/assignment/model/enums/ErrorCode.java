package com.everon.assignment.model.enums;

/**
 * Enum for all possible error codes.
 * @author Pooya Mirzapour (pooyamirzapour@gmail.com)
 */
public enum ErrorCode {

   ID_IS_NOT_VALID("Id is not valid",1001),
   ID_IS_ALREADY_STOPPED("Id is already stopped",1002),
   STATION_ID_IS_EMPTY("stationId is empty",1003);

    private int value;
    private String message;

     ErrorCode(String message ,int value) {
        this.value = value;
        this.message = message;
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
