package edu.cnm.deepdive.jata.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

/**
 * This class represents the game.
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(indexes = @Index(columnList = "game_id, boardSize, playerCount"))
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({""})
public class Game {

  @NonNull
  @Id
  @GeneratedValue
  @Column(name = "game_id", nullable = false, updatable = false)
  @JsonIgnore
  private Long id;

  @NonNull
  @Column(name = "external_key", nullable = false, updatable = false, unique = true, columnDefinition = "UUID")
  @JsonProperty(access = Access.READ_ONLY)
  private UUID key;

  @NonNull
  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @JsonProperty(access = Access.READ_ONLY)
  private Instant created;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "current_user_game_id")
  @JsonIgnore
  private UserGame currentUserGame;

  @NonNull
  @OneToMany(mappedBy = "game", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonProperty(access = Access.READ_ONLY)
  private final List<UserGame> userGames = new LinkedList<>();

  @NonNull
  @JsonProperty(access = Access.READ_WRITE)
  private int boardSize;

  @NonNull
  @JsonProperty(access = Access.READ_WRITE)
  private int playerCount;

  @JsonIgnore
  private long turnCount;

  @JsonIgnore
  private boolean finished;


  /**
   * Get game object's id
   *
   */
  public Long getId() {
    return id;
  }

  /**
   * get game's secure UUID key
   *
   */
  @NonNull
  public UUID getKey() {
    return key;
  }

  /**
   * Get the time and day the game was created.
   *
   */
  @NonNull
  public Instant getCreated() {
    return created;
  }

  /**
   * Returns the current user's userGame
   */
  public UserGame getCurrentUserGame() {
    return currentUserGame;
  }

  /**
   * Annotates the current user's userGame
   */
  public void setCurrentUserGame(UserGame currentUserGame) {
    this.currentUserGame = currentUserGame;
  }

  /**
   * get List of userGame.
   *
   */
  @NonNull
  public List<UserGame> getUserGames() {
    return userGames;
  }

  /**
   * Returns the size of the playing board's x-dimension
   *
   */
  public int getBoardSize() {
    return boardSize;
  }

  /**
   * Annotates the size of the playing board's x-dimension
   *
   */
  public void setBoardSize(int boardSize) {
    this.boardSize = boardSize;
  }

  /**
   * Get playerCount.
   *
   */
  public int getPlayerCount() {
    return playerCount;
  }

  /**
   * Returns the current turn count. A player's turn is when this turn count
   * matched the turn count in their userGame
   */
  public long getTurnCount() {
    return turnCount;
  }

  /**
   * Annotates the game's turn count
   */
  public void setTurnCount(long turnCount) {
    this.turnCount = turnCount;
  }

  /**
   * Returns a value to the users that this game has been filled and is in progress
   */
  public boolean isStarted() {
    return userGames.stream().filter((UserGame::isInventoryPlaced))
        .count() == playerCount;
  }

  /**
   * Returns a persisted flag indicating this game has been finished.
   */
  public boolean isFinished() {
    return (userGames.stream()
        .filter(UserGame::isFleetSunk)
        .count()) >= playerCount - 1;
  }

  /**
   * Annotates that this game has been finished
   */
  public void setFinished(boolean finished) {
    this.finished = finished;
  }

  /**
   * Returns flag indicating to the user that it is their turn
   */
  public boolean isUsersTurn() {
    return (currentUserGame.getTurnCount() == turnCount) && isStarted() && !isFinished();
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    boolean equals;
    if (this == obj) {
      equals = true;
    } else if (obj instanceof Game other) {
      equals = (this.key.equals(other.key));
    } else {
      equals = false;
    }
    return equals;
  }

  @PrePersist
  private void generateKey() {
    key = UUID.randomUUID();
  }
}
