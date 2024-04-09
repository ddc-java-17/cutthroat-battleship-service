package edu.cnm.deepdive.jata.service;

/**
 * Exception to let user know their proposed ship locations are invalid either for
 * being placed outside of the board edge or because two ships intersect each other
 */
public class InvalidShipLocationException extends IllegalStateException{

  /**
   * InvalidShipLocationException No parameter constructor
   */
  public InvalidShipLocationException() {
  }

  /**
   * InvalidShipLocationException Constructor with one string
   * @param s
   */
  public InvalidShipLocationException(String s) {
    super(s);
  }

  /**
   * InvalidShipLocationException Constructor with a string and a throwable
   * @param message
   * @param cause
   */
  public InvalidShipLocationException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * InvalidShipLocationException Constructor with a throwable
   * @param cause
   */
  public InvalidShipLocationException(Throwable cause) {
    super(cause);
  }
}
