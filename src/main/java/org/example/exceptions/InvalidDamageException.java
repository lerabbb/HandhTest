package org.example.exceptions;

import org.example.model.Constants;

public class InvalidDamageException extends InvalidValueException{
    public InvalidDamageException(int value, int max){
        super("damage", value, Constants.MIN_DAMAGE, max);
    }
}
