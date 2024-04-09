package edu.cnm.deepdive.jata.model;

/**
 * This enum contains the specifications of all legal ships for the game.  All sizes are represented
 * whether or not they are used in a given board size
 */
public enum ShipType {

  SMALL(2),
  MEDIUM(3),
  LARGE(4),
  X_LARGE(5),
  XX_LARGE(6);

  private final int shipSize;

  ShipType(int shipSize){
    this.shipSize = shipSize;
  }

  /**
   * Returns the size of a given ship
   * @return
   */
  public int getShipSize() {
    return shipSize;
  }
}
