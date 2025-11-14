package it.unibo.mvc.view;

import java.util.Objects;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

/**
 * Read-only Standard Output DrawNumber game view.
 */
public class DrawNumberStandardOutputView implements DrawNumberView {
    private DrawNumberController controller;

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(
        value = "EI2",
        justification = "Code provided by exercise"
    )
    public void setController(final DrawNumberController observer) {
        this.controller = observer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        System.out.println("Partita iniziata!"); // NOPMD Standard Output view needs to write on the Standard Output.
        Objects.requireNonNull(controller, "No controller attached to view.").resetGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void result(final DrawResult res) {
        System.out.println(res.getDescription()); // NOPMD Standard Output view needs to write on the Standard Output.
    }
}
