package edu.cnm.deepdive.jata.service;

public class InvalidShotPlacementException extends IllegalStateException {

  public InvalidShotPlacementException() {
  }

  public InvalidShotPlacementException(String s) {
    super(s);
  }

  public InvalidShotPlacementException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidShotPlacementException(Throwable cause) {
    super(cause);
  }

}
