package org.example.model;

import org.example.exceptions.HealException;
import org.example.model.entities.Entity;
import org.example.model.entities.Mob;
import org.example.model.entities.Player;
import org.example.model.enums.PlayerState;
import org.example.model.enums.TurnState;
import org.example.exceptions.InvalidValueException;

import java.util.*;

public class GameModel {
    public static final int MOBS_NUM = 3;
    public static final int NOT_ATTACKED_MOB = -1;

    private List<Entity> mobs;
    private Player player;
    private Random random;
    private int N, M;
    private int activeMobId;
    private int attackedMobId;
    private TurnState activeTurn;
    private PlayerState playerState;
    private boolean isVictory;
    private boolean isGameActive;
    private int roundNum;

    public GameModel() throws InvalidValueException {
        this.N = 10;
        this.M = 1;
        this.mobs = new ArrayList<>();
        this.random  = new Random();

        activeTurn = TurnState.PLAYER_TURN;
        activeMobId = 0;
        isGameActive = true;
        attackedMobId = NOT_ATTACKED_MOB;
        roundNum = 1;

        createMobs();
        createPlayer();
    }

    public List<Entity> getMobs() {
        return mobs;
    }
    public Player getPlayer() {
        return player;
    }
    public int getRoundNum(){
        return roundNum;
    }
    public int getAttackedMobId() {
        return attackedMobId;
    }
    public int getActiveMobId() {
        return activeMobId;
    }
    public TurnState getActiveTurn() {
        return activeTurn;
    }
    public boolean isVictory() {
        return isVictory;
    }
    public boolean isGameActive() {
        return isGameActive;
    }
    public void setAttackedMobId(int attackedMobId) {
        this.attackedMobId = attackedMobId;
    }
    public void setPlayerAttack() {
        this.playerState = PlayerState.ATTACK;
    }
    public void setPlayerHeal() {
        this.playerState = PlayerState.HEAL;
    }

    public void update() throws HealException {
        if(!isGameActive){
            return;
        }
        switch (activeTurn) {
            case PLAYER_TURN -> playerTurn();
            case MOB_TURN -> mobTurn();
        }
        checkGameState();
        roundNum++;
    }

    public int getAttackedMobHp(){
        return mobs.get(attackedMobId).getHitPoints();
    }
    public int getPlayerHp(){
        return player.getHitPoints();
    }

    private void playerTurn() throws HealException {
        if(playerState == PlayerState.ATTACK) {
            if (attackedMobId == NOT_ATTACKED_MOB) {
                return;
            }
            player.attack(mobs.get(attackedMobId));
        } else if (playerState == PlayerState.HEAL) {
            player.heal();
        }
        activeTurn = activeTurn.getOppositeTurn();
    }

    private void mobTurn(){
        mobs.get(activeMobId).attack(player);

        activeMobId = (activeMobId+1)%(mobs.size());
        activeTurn = activeTurn.getOppositeTurn();
    }

    private void checkGameState(){
        if(!player.isAlive()){
            isGameActive = false;
            isVictory = false;
            return;
        }
        boolean allMobsDead = true;
        for(Entity mob: mobs){
            if(mob.isAlive()){
               allMobsDead = false;
            }
        }
        if(allMobsDead){
            isGameActive = false;
            isVictory = true;
        }
    }

    private void createMobs() throws InvalidValueException {
        for(int i=0; i < MOBS_NUM; i++){
            mobs.add(new Mob(getRandomAttack(), getRandomArmor(), getRandomHp(), N, M, i));
        }
    }
    private void createPlayer() throws InvalidValueException {
        player = new Player(getRandomAttack(), getRandomArmor(), getRandomHp(), N, M);
    }
    private int getRandomAttack(){
        return randomVal(Constants.MIN_ATTACK, Constants.MAX_ATTACK);
    }
    private int getRandomHp(){
        return randomVal(1, N);
    }
    private int getRandomArmor(){
        return randomVal(Constants.MIN_ARMOR, Constants.MAX_ARMOR);
    }
    private int randomVal(int min, int max){
        return random.nextInt(max - min + 1) + min;
    }
}
