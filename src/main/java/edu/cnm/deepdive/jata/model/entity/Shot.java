package edu.cnm.deepdive.jata.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import java.time.Instant;
import java.util.Objects;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

/**
 * This class records every shot taken by every user in the game
 */
@Entity
@Table(
    indexes = {
        @Index(columnList = "shot_id, timestamp")
    }
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({""})
public class Shot {

  public static final int MIN_X_COORD = 1;
  public static final int MIN_Y_COORD = 1;

  @NonNull
  @Id
  @GeneratedValue
  @Column(name = "shot_id", nullable = false, updatable = false)
  @JsonIgnore
  private Long id;

  @NonNull
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "from_user_game_id")
  @JsonProperty(access = Access.READ_ONLY)
  private UserGame fromUser;

  @NonNull
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "to_user_game_id")
  @JsonProperty(access = Access.READ_WRITE)
  private UserGame toUser;

  @Column(nullable = false, updatable = true)
  @JsonProperty(access = Access.READ_WRITE)
  @Min(MIN_X_COORD)
  private int shotCoordX;

  @Column(nullable = false, updatable = true)
  @JsonProperty(access = Access.READ_WRITE)
  @Min(MIN_Y_COORD)
  private int shotCoordY;

  @NonNull
  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @JsonProperty(access = Access.READ_ONLY)
  private Instant timestamp;



  /**
   * Returns the unique ID of this shot
   *
   * @return id
   */
  @NonNull
  public Long getId() {
    return id;
  }

  /**
   * Returns the user who fired the shot
   *
   * @return fromUser
   */
  @NonNull
  public UserGame getFromUser() {
    return fromUser;
  }

  /**
   * Annotates the user who fired the shot
   *
   * @param fromUser
   */
  public void setFromUser(@NonNull UserGame fromUser) {
    this.fromUser = fromUser;
  }

  /**
   * Returns the user who was fired upon
   *
   * @return toUser
   */
  @NonNull
  public UserGame getToUser() {
    return toUser;
  }

  /**
   * Annotates the user who was fired upon
   *
   * @param toUser
   */
  public void setToUser(@NonNull UserGame toUser) {
    this.toUser = toUser;
  }


  /**
   * Returns the x-coordinate of this shot
   *
   * @return shotCoordX
   */
  public int getShotCoordX() {
    return shotCoordX;
  }

  /**
   * Annotates the x-coordinate of this shot
   *
   * @param xCoord
   */
  public void setShotCoordX(int xCoord) {
    this.shotCoordX = xCoord;
  }

  /**
   * Returns the y-coordinate of this shot
   *
   * @return shotCoordY
   */
  public int getShotCoordY() {
    return shotCoordY;
  }

  /**
   * Annotates the y-coordinate of this shot
   *
   * @param yCoord
   */
  public void setShotCoordY(int yCoord) {
    this.shotCoordY = yCoord;
  }

  /**
   * Returns the time the shot was fired
   *
   * @return timestamp
   */
  @NonNull
  public Instant getTimestamp() {
    return timestamp;
  }


  //public boolean isHit() {
  /**
   * Checks to see if a shot is a hit
   * @return boolean isHit
   */
  public boolean isHit() {
    return toUser.getLocations()
        .stream()
        .anyMatch((loc) -> loc.getShipCoordX() == shotCoordX &&
            loc.getShipCoordY() == shotCoordY);
  }

  public boolean isSunk() {
    return fromUser.getLocations()
        .stream()
        .allMatch((loc)-> loc.getShipCoordX() == shotCoordX &&
            loc.getShipCoordY() == shotCoordY);
  }

  @Override
  public int hashCode() {
    return Objects.hash(toUser, shotCoordX, shotCoordY);
  }

  @Override
  public boolean equals(Object obj) {
    boolean equals;
    if (this == obj) {
      equals = true;
    } else if (obj instanceof Shot other) {
      equals = (this.toUser.equals(other.toUser)
          && this.shotCoordX == (other.shotCoordX)
          && this.shotCoordY == (other.shotCoordY));
    } else {
      equals = false;
    }
    return equals;
  }

}


