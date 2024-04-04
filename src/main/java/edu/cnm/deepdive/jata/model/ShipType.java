package edu.cnm.deepdive.jata.model;

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

  public int getShipSize() {
    return shipSize;
  }
}
