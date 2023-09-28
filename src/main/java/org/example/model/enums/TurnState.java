package org.example.model.enums;

public enum TurnState {
    PLAYER_TURN, MOB_TURN;

    public TurnState getOppositeTurn(){
        return switch(this){
            case PLAYER_TURN -> MOB_TURN;
            case MOB_TURN -> PLAYER_TURN;
        };
    }
}
