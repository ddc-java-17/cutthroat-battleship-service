package edu.cnm.deepdive.jata.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import edu.cnm.deepdive.jata.view.ShotView;
import edu.cnm.deepdive.jata.view.UserGameView;
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
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.springframework.lang.NonNull;

/**
 * This class is the central hub of communication. All information the user needs comes through
 * this class.
 */

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "user_game", indexes = {
    @Index(columnList = "user_game_id, user_id"),
})
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"user_game_id"})
@JsonView({UserGameView.Summary.class, ShotView.Summary.class})
public class UserGame {

  @NonNull
  @Id
  @GeneratedValue
  @Column(name = "user_game_id", nullable = false, updatable = false)
  @JsonIgnore
  private Long id;

  @NonNull
  @Column(name = "external_key", nullable = false, updatable = false, unique = true, columnDefinition = "UUID")
  @JsonProperty(access = Access.READ_WRITE)
  private UUID key;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private User user;

  @NonNull
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "game_id", nullable = false, updatable = false)
  @JsonIgnore
  private Game game;

  @OneToMany(mappedBy = "userGame", fetch = FetchType.EAGER,
      cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private final List<ShipLocation> locations = new LinkedList<>();

  @OneToMany(mappedBy = "fromUser", fetch = FetchType.EAGER,
      cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private final List<Shot> fromShots = new LinkedList<>();

  @OneToMany(mappedBy = "toUser", fetch = FetchType.EAGER,
      cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonProperty(access = Access.READ_ONLY)
  @JsonView(UserGameView.Detailed.class)
  private final List<Shot> toShots = new LinkedList<>();

  @JsonProperty(access = Access.READ_WRITE)
  private boolean inventoryPlaced;

  @JsonIgnore
  private int turnCount;
  /**
   * Gets this UserGame's identifying number
   * @return id UserGame identification
   */
  @NonNull
  public Long getId() {
    return id;
  }

  /**
   * Gets the UserGame UUID key
   * @return UUID key
   */
  @NonNull
  public UUID getKey() {
    return key;
  }

  /**
   * Sets the UserGame UUID key
   * @param key UUID
   */
  public void setKey(@NonNull UUID key) {
    this.key = key;
  }

  /**
   * Gets the user object associated with this UserGame
   * @return user User object
   */
  @NonNull
  public User getUser() {
    return user;
  }

  /**
   * Sets the user object associated with this UserGame
   * @param user  User object.
   */
  public void setUser(@NonNull User user) {
    this.user = user;
  }

  /**
   * Gets the game object associated with this UserGame
   * @return game Game object
   */
  @NonNull
  public Game getGame() {
    return game;
  }

  /**
   * Sets the game associated to this UserGame
   * @param game  Game object
   */
  public void setGame(@NonNull Game game) {
    this.game = game;
  }

  /**
   * Returns a ships location
   * @return List <ShipLocation>
   */
  public List<ShipLocation> getLocations() {
    return locations;
  }

  /**
   * Returns shots from a specific user
   * @return list<shots>
   */
  public List<Shot> getFromShots() {
    return fromShots;
  }

  /**
   * returns shots at a specific user
   * @return List<shots>
   */
  public List<Shot> getToShots() {
    return toShots;
  }

  public boolean isInventoryPlaced() {
    return inventoryPlaced;
  }

  public void setInventoryPlaced(boolean placed){
    this.inventoryPlaced = placed;
  }

  public int getTurnCount() {
    return turnCount;
  }

  public void setTurnCount(int turnCount) {
    this.turnCount = turnCount;
  }

  @SuppressWarnings("ConstantValue")
  public int getShipLocationCount() {
    return locations.size();
  }

  public int getToShotHits() {
    return (int) toShots
        .stream()
        .filter(Shot::isHit)
        .distinct()
        .count();
  }

  public boolean isFleetSunk() {
    return getToShotHits() >= getShipLocationCount();
  }

  @JsonProperty("locations")
  public List<ShipLocation> getExposedLocations() {
    return isFleetSunk() ? locations : null;
  }

  @JsonProperty("myLocations")
  @JsonView(UserGameView.Detailed.class)
  public List<ShipLocation> getMyLocations() {
    return locations;
  }

  @Override
  public int hashCode() {
    return (id == null) ? Objects.hash(id) : Objects.hash(user, game);
  }

  @SuppressWarnings("ConstantValue")
  @Override
  public boolean equals(Object obj) {
    boolean equals;
    if (this == obj) {
      equals = true;
    } else if (obj instanceof UserGame other) {
      if ((this.id != null && other.id != null) && (this.id.equals(other.id))) {
        equals = true;
      } else {
        equals = (this.user.equals(other.user)
            && (this.game.equals(other.game)));
      }
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
