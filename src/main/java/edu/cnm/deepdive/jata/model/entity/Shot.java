package edu.cnm.deepdive.jata.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonView;
import edu.cnm.deepdive.jata.model.Location;
import edu.cnm.deepdive.jata.view.ShotView;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.Instant;
import java.util.Objects;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

/**
 * This class records every shot taken by every user in the game
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(
    indexes = {
        @Index(columnList = "shot_id, timestamp")
    }
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({""})
@JsonView(ShotView.Summary.class)
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
  @JsonView(ShotView.Detailed.class)
  private UserGame fromUser;

  @NonNull
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "to_user_game_id")
  @JsonProperty(access = Access.READ_WRITE)
  private UserGame toUser;

  @Column(nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_WRITE)
  @JsonUnwrapped
  private Location location;

  @NonNull
  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @JsonProperty(access = Access.READ_ONLY)
  @JsonView(ShotView.Detailed.class)
  private Instant timestamp;

  /**
   * Returns the unique ID of this shot
   *
   */
  @NonNull
  public Long getId() {
    return id;
  }

  /**
   * Returns the user who fired the shot
   *
   */
  @NonNull
  public UserGame getFromUser() {
    return fromUser;
  }

  /**
   * Annotates the user who fired the shot
   *
   */
  public void setFromUser(@NonNull UserGame fromUser) {
    this.fromUser = fromUser;
  }

  /**
   * Returns the user who was fired upon
   *
   */
  @NonNull
  public UserGame getToUser() {
    return toUser;
  }

  /**
   * Annotates the user who was fired upon
   *
   */
  public void setToUser(@NonNull UserGame toUser) {
    this.toUser = toUser;
  }

  /**
   * Returns the x and y coordinates of a particular shot
   *
   */
  public Location getLocation() {
    return location;
  }

  /**
   * Annotates the x and y coordinates of a particular shot
   *
   */
  public void setLocation(Location location) {
    this.location = location;
  }

  /**
   * Returns the time the shot was fired
   *
   */
  @NonNull
  public Instant getTimestamp() {
    return timestamp;
  }

  /**
   * Returns a flag indicating this shot hit a ship on that board by all ship locations
   * and looking for any hits in the shot table
   *
   */
  public boolean isHit() {
    return toUser
        .getLocations()
        .stream()
        .anyMatch((shotLoc) -> {
          Location location1 = shotLoc.getLocation();
          return location1.getX() == location.getX()
              && location1.getY() == location.getY();
        });
  }


  @Override
  public int hashCode() {
    return Objects.hash(toUser, location);
  }

  @SuppressWarnings("ConstantValue")
  @Override
  public boolean equals(Object obj) {
    boolean equals;
    if (this == obj) {
      equals = true;
    } else if (obj instanceof Shot other) {
      equals = this.toUser.equals(other.toUser) && this.location.equals(other.location);
    } else {
      equals = false;
    }
    return equals;
  }

}


