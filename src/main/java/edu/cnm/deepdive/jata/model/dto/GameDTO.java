package edu.cnm.deepdive.jata.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import java.util.List;
import java.util.UUID;

/**
 * This serves as the interface between the outside world and the server internals for the Game
 * entity.  The GameDTO determines what a user may see.
 */
public class GameDTO {

  private UUID key;

  @JsonProperty(value = "boards")
  private List<UserGameDTO> userGames;

  private int boardSize;

  private int playerCount;

  private boolean started;

  private boolean finished;

  @JsonProperty(value = "yourTurn")
  private boolean usersTurn;

  /**
   * Default, no parameter constructor
   */
  public GameDTO() {
  }

  /**
   * This constructor is populated by Game and has the logic for determining whose turn
   * it is.
   * @param game
   * @param userGame
   */
  public GameDTO(Game game, UserGame userGame) {
    key = game.getKey();
    boardSize = game.getBoardSize();
    playerCount = game.getPlayerCount();
    started = game.isStarted();
    finished = game.isFinished();
    userGames = game.getUserGames().stream()
        .map(userGame1 -> new UserGameDTO(userGame1, userGame))
        .toList();
    usersTurn = (userGame.getTurnCount() == game.getTurnCount()) && isStarted() && !isFinished();
  }

  /**
   * Returns the UUID game key
   * @return
   */
  public UUID getKey() {
    return key;
  }

  /**
   * Returns the list of UserGames in this specific game
   *
   * @return
   */
  public List<UserGameDTO> getUserGames() {
    return userGames;
  }

  /**
   * Annotates the userGames in this game
   *
   * @param userGames
   */
  public void setUserGames(List<UserGameDTO> userGames) {
    this.userGames = userGames;
  }

  /**
   * Returns the board size for this game
   * @return
   */
  public int getBoardSize() {
    return boardSize;
  }

  /**
   * Anotates the board size for this game
   * @param boardSize
   */
  public void setBoardSize(int boardSize) {
    this.boardSize = boardSize;
  }

  /**
   * Returns the number of players in this game.  This is the number of players that
   * must join for the game to start
   * @return
   */
  public int getPlayerCount() {
    return playerCount;
  }

  /**
   * Annotates the player count
   * @param playerCount
   */
  public void setPlayerCount(int playerCount) {
    this.playerCount = playerCount;
  }

  /**
   * Returns the started flag.  This flag is set when number of users joined equals
   * the player count and all users have placed their ships
   * @return
   */
  public boolean isStarted() {
    return started;
  }

  /**
   * Annotates the started flag
   * @param started
   */
  public void setStarted(boolean started) {
    this.started = started;
  }

  /**
   * Returns the Finished flag.  THis flag is set when only one user remains whose fleet
   * does not have the Sunk flag set
   * @return
   */
  public boolean isFinished() {
    return finished;
  }

  /**
   * Annotates the finished flag
   *
   * @param finished
   */
  public void setFinished(boolean finished) {
    this.finished = finished;
  }

  /**
   * Returns the usersTurn flag.  This flag is set for a particular userGame when the
   * turn count of the usergame matches the turn count of the game.
   *
   * @return
   */
  public boolean isUsersTurn() {
    return usersTurn;
  }

  /**
   * Annotates the users turn flag
   * @param usersTurn
   */
  public void setUsersTurn(boolean usersTurn) {
    this.usersTurn = usersTurn;
  }
}
