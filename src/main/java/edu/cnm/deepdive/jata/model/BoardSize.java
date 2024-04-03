package edu.cnm.deepdive.jata.model;

public enum BoardSize {

  SMALL(8, 8),
  MEDIUM(9, 9),
  LARGE(10, 10);

  private final int boardSizeX;
  private final int boardSizeY;

  BoardSize(int boardSizeX, int boardSizeY){
    this.boardSizeX = boardSizeX;
    this.boardSizeY = boardSizeY;
  }

  public int getBoardSizeX() {
    return boardSizeX;
  }

  public int getBoardSizeY() {
    return boardSizeY;
  }
}
