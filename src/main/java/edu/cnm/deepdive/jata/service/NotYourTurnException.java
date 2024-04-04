package edu.cnm.deepdive.jata.service;

public class NotYourTurnException extends IllegalStateException{

  public NotYourTurnException() {
  }

  public NotYourTurnException(String s) {
    super(s);
  }

  public NotYourTurnException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotYourTurnException(Throwable cause) {
    super(cause);
  }
}
