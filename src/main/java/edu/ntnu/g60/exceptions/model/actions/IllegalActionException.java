package edu.ntnu.g60.exceptions.model.actions;

/**
 * This exception is thrown when an action is executed on a null player.
 * 
 * @see Action, GoldAction, ScoreAction, InventoryAction and ActionFactory
 * @author Stian Lyng
 */
public class IllegalActionException extends RuntimeException {

    /**
     * Creates a new IllegalActionException.
     * 
     * @param message The message to display.
     */
    public IllegalActionException(String message) {
        super(message);
    }
}