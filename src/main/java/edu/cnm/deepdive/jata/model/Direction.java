package edu.cnm.deepdive.jata.model;

/**
 * This enum maintains the indicies for horzontal and vertical ships.  These indicies are used
 * to translate the ships from vector format to cartesian format and back.
 */
public enum Direction {

  HORIZONTAL(0,1),
  VERTICAL(1,0);

  private final int verticalIndex;
  private final int horizontalIndex;

  Direction(int verticalIndex, int horizontalIndex) {
    this.verticalIndex = verticalIndex;
    this.horizontalIndex = horizontalIndex;
  }

  /**
   * Retuns the vertical index
    * @return
   */
  public int getVerticalIndex() {
    return verticalIndex;
  }

  /**
   * returns the horizontal index
   * @return
   */
  public int getHorizontalIndex() {
    return horizontalIndex;
  }
}
