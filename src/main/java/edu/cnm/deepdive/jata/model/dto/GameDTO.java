package edu.cnm.deepdive.jata.model.dto;

import edu.cnm.deepdive.jata.model.entity.UserGame;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class GameDTO {

  private UUID key;

  private final List<UserGame> userGames = new LinkedList<>();

  private int boardSize;

  private int playerCount;

  private boolean started;

  private boolean finished;


  public UUID getKey() {
    return key;
  }

  public List<UserGame> getUserGames() {
    return userGames;
  }

  public int getBoardSize() {
    return boardSize;
  }

  public void setBoardSize(int boardSize) {
    this.boardSize = boardSize;
  }

  public int getPlayerCount() {
    return playerCount;
  }

  public void setPlayerCount(int playerCount) {
    this.playerCount = playerCount;
  }

  public boolean isStarted() {
    return started;
  }

  public void setStarted(boolean started) {
    this.started = started;
  }

  public boolean isFinished() {
    return finished;
  }

  public void setFinished(boolean finished) {
    this.finished = finished;
  }
}
