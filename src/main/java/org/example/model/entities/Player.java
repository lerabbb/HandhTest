package org.example.model.entities;

import org.example.exceptions.HealException;
import org.example.exceptions.InvalidValueException;
import org.example.model.Constants;

public class Player extends Entity{
    private int healCount;

    public Player(int attack, int armor, int hitPoints, int N, int M) throws InvalidValueException {
        super(attack, armor, hitPoints, N, M);
        healCount = 0;
    }

    public void heal() throws HealException {
        if(healCount < Constants.MAX_HEAL_COUNT){
            if(hitPoints>0 && hitPoints<3){
                hitPoints++;
            }
            else {
                hitPoints += (int) (Constants.HEAL_PART * maxHp);
            }
            healCount++;
        }
        else {
            throw new HealException();
        }
    }


    @Override
    public String toString() {
        return "\tINFO [PLAYER]:\tattack = " + attack + "\tarmor = "+ armor +
                "\thp = " + hitPoints + "\thealCount = " + healCount;
    }
}
