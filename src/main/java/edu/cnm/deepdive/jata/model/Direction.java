package edu.cnm.deepdive.jata.model;

public enum Direction {

  HORIZONTAL(0,1),
  VERTICAL(1,0);

  private final int verticalIndex;
  private final int horizontalIndex;

  Direction(int verticalIndex, int horizontalIndex) {
    this.verticalIndex = verticalIndex;
    this.horizontalIndex = horizontalIndex;
  }

  public int getVerticalIndex() {
    return verticalIndex;
  }

  public int getHorizontalIndex() {
    return horizontalIndex;
  }
}
