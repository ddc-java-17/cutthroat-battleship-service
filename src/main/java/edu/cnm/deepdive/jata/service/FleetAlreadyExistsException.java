package edu.cnm.deepdive.jata.service;

public class FleetAlreadyExistsException extends IllegalStateException{

  public FleetAlreadyExistsException() {
  }

  public FleetAlreadyExistsException(String s) {
    super(s);
  }

  public FleetAlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }

  public FleetAlreadyExistsException(Throwable cause) {
    super(cause);
  }
}
