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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.LinkedList;
import java.util.List;
import org.springframework.lang.NonNull;

/**
 * The Fleet entity holds the relationship between a user, a game, and a list of ships.
 */
@Entity
@Table(indexes = @Index(columnList = "fleet_id, user_game_id"))
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"fleet_id", "user_game_id", ""})
public class Fleet {

  @NonNull
  @Id
  @GeneratedValue
  @Column(name = "fleet_id", nullable = false, updatable = false)
  @JsonIgnore
  private Long id;

  @NonNull
  @OneToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "user_game_id", nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private UserGame userGame;

  @NonNull
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "game_id", nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private Game game;

  @NonNull
  @OneToMany(mappedBy = "fleet", fetch = FetchType.EAGER,
      cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonProperty(access = Access.READ_ONLY)
  private final List<Ship> ships = new LinkedList<>();

  /**
   * Returns the primary key of the Fleet
   *
   * @return
   */
  @NonNull
  public Long getId() {
    return id;
  }

  /**
   * Returns the UserGame entity associated with a fleet
   * @return
   */
  @NonNull
  public UserGame getUserGame() {
    return userGame;
  }

  /**
   * Annotates a fleet with a UserGame
   * @param userGame
   */
  public void setUserGame(@NonNull UserGame userGame) {
    this.userGame = userGame;
  }

  /**
   * Returns that game associated with the fleets
   * @return
   */
  @NonNull
  public Game getGame() {
    return game;
  }

  /**
   * Assigns a Game to this fleet
   *
   * @param game
   */
  public void setGame(@NonNull Game game) {
    this.game = game;
  }

  /**
   * Returns a list of ships associated with this Fleet
   *
   * @return
   */
  @NonNull
  public List<Ship> getShips() {
    return ships;
  }
}
