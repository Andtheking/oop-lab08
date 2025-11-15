package it.unibo.mvc;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerMultipleViewsImpl;
import it.unibo.mvc.model.DrawNumberImpl;

/**
 * Application entry-point.
 */
public final class LaunchApp {
    static final int HOW_MUCH_VIEWS = 3;

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args) throws 
        ClassNotFoundException, 
        NoSuchMethodException, 
        InstantiationException, 
        InvocationTargetException, 
        IllegalAccessException {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerMultipleViewsImpl(model);
        final var views = List.of(
            "it.unibo.mvc.view.DrawNumberStandardOutputView",
            "it.unibo.mvc.view.DrawNumberSwingView"
        );
        for (final String viewName : views) {
            for (int i = 0; i < HOW_MUCH_VIEWS; i++) {
                app.addView((DrawNumberView) Class.forName(viewName).getConstructor().newInstance());
            }
        }
    }
}
