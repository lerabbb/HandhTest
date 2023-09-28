package org.example.exceptions;

import org.example.model.Constants;

public class InvalidHitPointsException extends InvalidValueException{
    public InvalidHitPointsException(int value, int max){
        super("hitPoints", value, Constants.MIN_HIT_POINT, max);
    }

}
