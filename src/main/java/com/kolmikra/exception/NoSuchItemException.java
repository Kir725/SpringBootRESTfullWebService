package com.kolmikra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "there is no element with such id")
public class NoSuchItemException extends Exception{
    String message;

    public NoSuchItemException() {
    }

    NoSuchItemException(String str) {
        message = str;
    }

    public String toString() {
        return ("Exception Occurred: " + message);
    }
}
