package edu.ntnu.g60.exceptions.model.actions;

/**
 * This exception is thrown when an invalid action type is used.
 * Especially used when parsing the JSON from the StoryParser and then the ActionFactory.
 *
 * @see ActionTypeEnum and ActionFactory
 * @author Stian Lyng
 */
public class InvalidActionTypeException extends IllegalArgumentException {

  /**
   * Creates a new InvalidActionTypeException.
   *
   * @param message The message to display.
   */
  public InvalidActionTypeException(String message) {
    super(message);
  }
}
