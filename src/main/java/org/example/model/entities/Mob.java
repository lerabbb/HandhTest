package org.example.model.entities;

import org.example.exceptions.InvalidValueException;

public class Mob extends Entity{
    private int id;

    public Mob(int attack, int armor, int hitPoints, int N, int M, int id) throws InvalidValueException {
        super(attack, armor, hitPoints, N, M);
        this.id = id;
    }

    @Override
    public String toString() {
        if(isAlive){
            return "\tINFO [MOB " + (id+1) + "]:\tattack = " + attack + "\tarmor = "+ armor + "\thp = " + hitPoints;
        } else{
            return "\tINFO [MOB " + (id+1) + "]:\tdead";
        }
    }
}
