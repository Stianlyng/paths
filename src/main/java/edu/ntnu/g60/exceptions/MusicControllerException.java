package edu.ntnu.g60.exceptions;

public class MusicControllerException extends Exception {

  // Constructor that accepts a specific message
  public MusicControllerException(String message) {
    super(message);
  }

  // Constructor that accepts a message and a Throwable cause
  public MusicControllerException(String message, Throwable cause) {
    super(message, cause);
  }
}
