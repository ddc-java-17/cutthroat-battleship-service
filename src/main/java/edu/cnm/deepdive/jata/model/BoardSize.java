package edu.cnm.deepdive.jata.model;

import java.util.Map;

/**
 * This enum specifies the only valid values of board size.  Once a board size is determined,
 * the type and number of ships is given
 */
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

  /**
   * Returns the value of board size in the x direction
   * @return
   */
  public int getBoardSizeX() {
    return boardSizeX;
  }

  /**
   * Returns the value of board size in the y-direction
   * @return
   */
  public int getBoardSizeY() {
    return boardSizeY;
  }

  /**
   * Returns the inventory of ship for a given board size.  THis will cascade into the ShipType
   * enum
   *
   * @return
   */
  public Map<ShipType, Integer> getInventory() {
    return inventory;
  }

  /**
   * This determines the closest legal match to a users requested board size.
   *
   * @param requestedSize
   * @return
   */
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
