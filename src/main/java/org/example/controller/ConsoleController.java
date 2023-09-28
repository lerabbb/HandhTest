package org.example.controller;

import org.example.exceptions.HealException;
import org.example.model.Constants;
import org.example.model.GameModel;
import org.example.model.enums.TurnState;
import org.example.view.View;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleController implements Controller{
    private static final int SLEEP_MILLIS = 2000;
    private final View view;
    private final GameModel gameModel;
    private final Scanner scanner;
    private String option;

    public ConsoleController(GameModel model, View view){
        this.gameModel = model;
        this.view = view;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run () {
        try {
            view.showMessage("Game started!");
            Thread.sleep(SLEEP_MILLIS);

            while (gameModel.isGameActive()) {
                view.showInfo();
                Thread.sleep(SLEEP_MILLIS);
                if (gameModel.getActiveTurn() == TurnState.PLAYER_TURN) {
                    view.showInstructions();
                    getChoiceInput();
                }
                gameModel.update();
                onModelUpdate();
                Thread.sleep(SLEEP_MILLIS);
            }

            if (gameModel.isVictory()) {
                view.showVictory();
            } else {
                view.showDefeat();
            }
        } catch(InterruptedException | HealException e){
            view.showMessage(e.getMessage());
        }
    }

    private void getChoiceInput(){
        option = scanner.nextLine();
        if(Constants.END_INPUT.equals(option)){
            quit();
        }
        if(Constants.HEAL_INPUT.equals(option)){
            gameModel.setPlayerHeal();
        }
        else if(isNumber(option)){
            gameModel.setPlayerAttack();
            gameModel.setAttackedMobId(Integer.parseInt(option)-1);
        }
    }

    private void onModelUpdate(){
        if(gameModel.getActiveTurn() == TurnState.MOB_TURN){
            if(Constants.HEAL_INPUT.equals(option)){
                view.showMessage("Your hit points have increased by 30%. Now hp=" + gameModel.getPlayerHp());
            }
            else if(isNumber(option)){
                view.showMessage("You have damaged mob "+ (gameModel.getAttackedMobId()+1) + ". Now it has hp=" + gameModel.getAttackedMobHp());
            }
        }
        else{
            view.showMessage("Mob " + (gameModel.getActiveMobId()+1) + " has damaged you. Now your hp="+gameModel.getPlayerHp());
        }
    }

    private boolean isNumber(String str){
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    @Override
    public void quit(){
        view.showMessage("Goodbye!");
        System.exit(0);
    }
}
