package edu.cnm.deepdive.jata.model;

import java.util.Map;

public enum BoardSize {

  SMALL(8, 8,
      Map.of(ShipType.SMALL, 1,
          ShipType.MEDIUM, 2,
          ShipType.LARGE, 1)),
  MEDIUM(10, 10,
      Map.of(ShipType.SMALL, 1,
          ShipType.MEDIUM, 2,
          ShipType.LARGE, 1,
          ShipType.X_LARGE, 1)),

  LARGE(12, 12,
      Map.of(ShipType.SMALL, 1,
          ShipType.MEDIUM, 2,
          ShipType.LARGE, 1,
          ShipType.X_LARGE, 1,
          ShipType.XX_LARGE, 1));

  private final int boardSizeX;
  private final int boardSizeY;

  private final Map<ShipType, Integer> inventory;

  BoardSize(int boardSizeX, int boardSizeY, Map<ShipType, Integer> inventory) {
    this.boardSizeX = boardSizeX;
    this.boardSizeY = boardSizeY;
    this.inventory = inventory;
  }

  public int getBoardSizeX() {
    return boardSizeX;
  }

  public int getBoardSizeY() {
    return boardSizeY;
  }

  public Map<ShipType, Integer> getInventory() {
    return inventory;
  }

  public static BoardSize closestMatch(int requestedSize){
    if(requestedSize <= SMALL.getBoardSizeX()) {
      return SMALL;
    } else if (requestedSize >= LARGE.getBoardSizeX()) {
      return LARGE;
    } else {
      return MEDIUM;
    }

  }
}
