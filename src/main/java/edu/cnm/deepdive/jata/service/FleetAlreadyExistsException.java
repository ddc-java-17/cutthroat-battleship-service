package edu.cnm.deepdive.jata.service;

/**
 * Exception that tells user they are trying to place more ships when they already
 * have a fleet placed.
 */
public class FleetAlreadyExistsException extends IllegalStateException{

  /**
   * FleetAlreadyExistsException No parameter constructor
   *
   */
  public FleetAlreadyExistsException() {
  }

  /**
   * FleetAlreadyExistsException Constructor with one string
   *
   * @param s
   */
  public FleetAlreadyExistsException(String s) {
    super(s);
  }

  /**
   * FleetAlreadyExistsException Constructor with a string and a throwable
   *
   * @param message
   * @param cause
   */
  public FleetAlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * FleetAlreadyExistsException Constructor with a throwable
   *
   * @param cause
   */
  public FleetAlreadyExistsException(Throwable cause) {
    super(cause);
  }
}
