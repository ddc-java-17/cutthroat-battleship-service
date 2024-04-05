package edu.cnm.deepdive.jata.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.cnm.deepdive.jata.model.entity.Game;
import java.util.List;
import java.util.UUID;

public class GameDTO {

  private UUID key;

  @JsonProperty(value = "boards")
  private List<UserGameDTO> userGames;

  private int boardSize;

  private int playerCount;

  private boolean started;

  private boolean finished;

  @JsonProperty(value = "yourTurn")
  private boolean isTurn;

  public GameDTO() {
  }
  public GameDTO(Game game) {
    key = game.getKey();
    boardSize = game.getBoardSize();
    playerCount = game.getPlayerCount();
    started = game.isStarted();
    finished = game.isFinished();
    userGames = game.getUserGames().stream()
        .map(UserGameDTO::new)
        .toList();
  }




  public UUID getKey() {
    return key;
  }

  public List<UserGameDTO> getUserGames() {
    return userGames;
  }

  public void setUserGames(List<UserGameDTO> userGames) {
    this.userGames = userGames;
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

  public boolean isTurn() {
    return isTurn;
  }

  public void setTurn(boolean turn) {
    isTurn = turn;
  }
}