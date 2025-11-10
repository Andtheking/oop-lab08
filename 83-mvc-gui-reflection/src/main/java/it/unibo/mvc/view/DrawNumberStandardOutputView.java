package it.unibo.mvc.view;

import java.io.IOException;

import it.unibo.mvc.api.DrawNumber;
import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.Configuration;



public class DrawNumberStandardOutputView implements DrawNumberView {
    private DrawNumberController observer;

    @Override
    public void setController(DrawNumberController observer) {
        this.observer = observer;
    }

    @Override
    public void start() {
        System.out.print("Partita iniziata! ");
        observer.resetGame();
        gameLoop();
    }

    @Override
    public void result(DrawResult res) {
        
    }

    private void gameLoop() {
        while (true) {
            System.out.print("Inserisci il numero --> ");
            String inputString = System.console().readLine();
            try {
                observer.newAttempt(Integer.parseInt(inputString));
            } catch (NumberFormatException nfe) {
                System.out.println("Non hai inserito un numero, riprova.");
            }
        }
    }

}