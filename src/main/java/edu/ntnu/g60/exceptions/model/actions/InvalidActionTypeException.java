package edu.ntnu.g60.exceptions.model.actions;

public class InvalidActionTypeException extends IllegalArgumentException {
    public InvalidActionTypeException(String message) {
        super(message);
    }
}