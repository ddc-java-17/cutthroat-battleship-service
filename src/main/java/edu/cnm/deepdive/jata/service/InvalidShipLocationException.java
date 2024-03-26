package edu.cnm.deepdive.jata.service;

public class InvalidShipLocationException extends IllegalStateException{

  public InvalidShipLocationException() {
  }

  public InvalidShipLocationException(String s) {
    super(s);
  }

  public InvalidShipLocationException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidShipLocationException(Throwable cause) {
    super(cause);
  }
}
