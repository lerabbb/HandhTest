package org.example.exceptions;

import org.example.model.Constants;

public class InvalidAttackException extends InvalidValueException{
    public InvalidAttackException(int value){
        super("attack", value, Constants.MIN_ATTACK, Constants.MAX_ATTACK);
    }
}
