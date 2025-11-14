package it.unibo.mvc.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.mvc.api.DrawNumber;
import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

/**
 * Represents a controller with the possibility to attach multiple views.
 */
public class DrawNumberControllerMultipleViewsImpl implements DrawNumberController {

    private final DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * Create a controller with multiple views.
     * 
     * @param model
     *              model of the game
     */
    public DrawNumberControllerMultipleViewsImpl(final DrawNumber model) {
        this.model = model;
        this.views = new LinkedList<>();
    }

    /**
     * {@inheritDoc} 
     * Comunicates the result to every view.
     */
    @Override
    public void newAttempt(final int n) {
        if (views.isEmpty()) {
            throw new IllegalStateException("There's no view attached to the controller");
        }
        final DrawResult result = model.attempt(n);
        for (final DrawNumberView drawNumberView : views) {
            drawNumberView.result(result);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetGame() {
        this.model.reset();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(
        value = "DM_EXIT",
        justification = "Required by exercise. Could be improved declaring \"quit\" method in DrawNumberView."
    )
    public void quit() {
        System.exit(0);
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public void addView(final DrawNumberView newView) {
        Objects.requireNonNull(newView, "Cannot set a null view");
        views.add(newView);
        newView.setController(this);
        newView.start();
    }
}
