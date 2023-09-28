package org.example.model.entities;

import org.example.exceptions.*;
import org.example.model.Constants;

import java.util.Random;

public abstract class Entity {
    protected final int attack;
    protected final int armor;
    protected final int maxHp;
    protected final int minDamage, maxDamage;
    protected int hitPoints;
    protected boolean isAlive;
    private final Random random;

    public Entity(int attack, int armor, int hitPoints, int N, int M) throws InvalidValueException {
        if(valueIsWrong(attack, Constants.MIN_ATTACK, Constants.MAX_ATTACK)){
            throw new InvalidAttackException(attack);
        }
        if(valueIsWrong(armor, Constants.MIN_ARMOR, Constants.MAX_ARMOR)){
            throw new InvalidArmorException(armor);
        }
        if(valueIsWrong(hitPoints, Constants.MIN_HIT_POINT, N)){
            throw new InvalidHitPointsException(hitPoints, N);
        }
        if(M >= N){
            throw new InvalidDamageException(M, N);
        }

        this.attack = attack;
        this.armor = armor;
        this.hitPoints = hitPoints;
        this.maxHp = hitPoints;
        this.minDamage = M;
        this.maxDamage = N;
        this.isAlive = true;
        random = new Random();
    }
    public int getAttack(){
        return attack;
    }
    public int getArmor(){
        return armor;
    }
    public int getHitPoints() {
        return hitPoints;
    }
    public boolean isAlive() {
        return isAlive && hitPoints>0;
    }
    public int getDamagePoint() {
        return random.nextInt(maxDamage - minDamage + 1) + minDamage;
    }

    public void decreaseHitPoints(int damage){
        if(damage <= 0){
            return;
        }
        hitPoints -= damage;
        if(hitPoints <= 0){
            hitPoints = 0;
            isAlive = false; //entity dies
        }
    }

    //def entity is defending, this entity is attacking
    public void attack(Entity def){
        int attackModifier = this.attack - def.armor + 1;
        if(isSuccessAttack(attackModifier)){
            def.decreaseHitPoints(this.getDamagePoint());
        }
    }

    private boolean isSuccessAttack(int diceNum){
        boolean isSuccess = false;
        int dicePoint;
        for(int i = 0; i < diceNum; i++){
            dicePoint = random.nextInt(Constants.MAX_DICE_POINT - Constants.MIN_DICE_POINT + 1) + Constants.MIN_DICE_POINT;
            if(dicePoint >= Constants.MIN_SUCCESS_POINT){
                isSuccess = true;
            }
        }
        return isSuccess;
    }
    private boolean valueIsWrong(int value, int min, int max){
        return value < min || value > max;
    }
}
