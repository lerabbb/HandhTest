package org.example.exceptions;

import org.example.model.Constants;
import org.example.view.ConsoleView;

public class HealException extends Exception{
    public HealException(){
        super("Impossible to heal more than " + Constants.MAX_HEAL_COUNT);
    }
}
