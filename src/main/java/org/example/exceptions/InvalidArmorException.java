package org.example.exceptions;

import org.example.model.Constants;

public class InvalidArmorException extends InvalidValueException{
    public InvalidArmorException(int value){
        super("armor", value, Constants.MIN_ARMOR, Constants.MAX_ARMOR);
    }
}
