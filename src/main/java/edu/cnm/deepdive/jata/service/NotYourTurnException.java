package edu.cnm.deepdive.jata.service;

/**
 * Exception to let user know they are trying to fire shots when it isn't their turn
 */
public class NotYourTurnException extends IllegalStateException{

  /**
   * NotYourTurnException No parameter constructor
   *
   */
  public NotYourTurnException() {
  }

  /**
   * NotYourTurnException Constructor with one string
   *
   */
  public NotYourTurnException(String s) {
    super(s);
  }

  /**
   * NotYourTurnException Constructor with a string and a throwable
   *
   * @param message
   * @param cause
   */
  public NotYourTurnException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * NotYourTurnException Constructor with a throwable
   *
   * @param cause
   */
  public NotYourTurnException(Throwable cause) {
    super(cause);
  }
}
