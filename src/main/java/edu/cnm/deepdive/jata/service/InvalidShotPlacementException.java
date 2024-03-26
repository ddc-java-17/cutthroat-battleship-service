package edu.cnm.deepdive.jata.service;

/**
 * This class is used to throw an exception when a shot is placed in an invalid location.
 */
public class InvalidShotPlacementException extends IllegalStateException {

  /**
   * An exception for an invalid shot.
   */
  public InvalidShotPlacementException() {
  }

  /**
   * exception that is thrown when a spot is placed off the board.
   * @param s String
   */
  public InvalidShotPlacementException(String s) {
    super(s);
  }

  /**
   * Exception that is thrown for an invalid shot.
   * @param message "Shot placement invalid"
   * @param cause Caused by a shot being placed off the board.
   */
  public InvalidShotPlacementException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Invalid shot exception that is thrown.
   * @param cause caused by a shot being placed in an invalid location.
   */
  public InvalidShotPlacementException(Throwable cause) {
    super(cause);
  }

}
