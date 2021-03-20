package com.github.oliverschen.enums;


/**
 * @author ck
 */
public class ExceptionEnum {

    private final Integer code;
    private final String message;

    public ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
