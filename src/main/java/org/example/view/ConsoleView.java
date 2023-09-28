package org.example.view;

import org.example.model.GameModel;
import org.example.model.entities.Entity;

public class ConsoleView implements View{
    private GameModel model;

    public ConsoleView(GameModel model){
        this.model = model;
    }

    @Override
    public void showInfo() {
        System.out.println("\tROUND " + model.getRoundNum());
        for(Entity mob: model.getMobs()){
            System.out.println(mob.toString());
        }
        System.out.println(model.getPlayer().toString());
    }

    @Override
    public void showInstructions() {
        System.out.println("Your turn. You can attack a mob, heal yourself or quit the game.");
        System.out.println(" * To attack: enter a [mob id]");
        System.out.println(" * To heal: enter ['heal']");
        System.out.println(" * To quit: enter ['end']");
    }

    @Override
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    @Override
    public void showVictory(){
        System.out.println("You won!");
    }
    @Override
    public void showDefeat(){
        System.out.println("Game over");
    }
}
