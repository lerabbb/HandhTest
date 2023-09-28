package org.example;

import org.example.controller.ConsoleController;
import org.example.controller.Controller;
import org.example.exceptions.InvalidValueException;
import org.example.model.GameModel;
import org.example.view.ConsoleView;
import org.example.view.View;


public class Main {
    public static void main(String[] args) {
        try {
            GameModel model = new GameModel();
            View view = new ConsoleView(model);
            Controller controller = new ConsoleController(model, view);
            controller.run();
        } catch (InvalidValueException e){
            System.out.println(e.getMessage());
        }
    }
}