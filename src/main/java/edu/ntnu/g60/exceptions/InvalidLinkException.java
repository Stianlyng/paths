package edu.ntnu.g60.exceptions;

/**
 * Exception thrown when a link is invalid.
 *
 * @Author: Stian Lyng
 */
public class InvalidLinkException extends Exception {

  public InvalidLinkException(String message) {
    super(message);
  }
}
