package edu.cnm.deepdive.jata.controller;

import edu.cnm.deepdive.jata.service.FleetAlreadyExistsException;
import edu.cnm.deepdive.jata.service.InvalidShipLocationException;
import edu.cnm.deepdive.jata.service.InvalidShotPlacementException;
import edu.cnm.deepdive.jata.service.NotYourTurnException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This is where we have added in out own custom exceptions to throw.
 */
@RestControllerAdvice
public class ExceptionWrangler {

  /**
   * This exception informs the sender that a ship in their fleet either was outside the
   * playing board boundaries, or intersects another of their own ships
   */
  @ExceptionHandler(InvalidShipLocationException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Ship placement invalid, either outside the world or intersecting another ship")
  public void invalidShipLocation() {
  }

  ;

  /**
   * This exception informs the sender they already have ships on the board and may not add more
   */
  @ExceptionHandler(FleetAlreadyExistsException.class)
  @ResponseStatus(code = HttpStatus.CONFLICT, reason = "User has already placed ships")
  public void fleetAlreadyExists() {
  }

  ;

  /**
   * This exception informs the sender that their shot is invalid when placed off the board.
   */
  @ExceptionHandler(InvalidShotPlacementException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Shot placement invalid")
  public void invalidShotPlacement() {
  }

  /**
   * This exception informs a user they have tried to fire shots when it is not yet their turn
   */
  @ExceptionHandler(NotYourTurnException.class)
  @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Not the user's turn to fire")
  public void notYourTurn() {}
}
