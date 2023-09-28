package org.example.exceptions;

public class InvalidValueException extends Exception{
    public InvalidValueException(String paramName, int value, int min, int max){
        super("Invalid " + paramName + ": expected value in " + min + ".." + max + "but actual is " + value);
    }
}
