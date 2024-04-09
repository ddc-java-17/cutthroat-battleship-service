package edu.cnm.deepdive.jata.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import edu.cnm.deepdive.jata.model.Location;
import edu.cnm.deepdive.jata.model.entity.Shot;
import edu.cnm.deepdive.jata.model.entity.UserGame;

/**
 * This serves as the interface for users to get and receive shots.  This also determines if
 * a shot has hit anything
 */
public class ShotDTO {

  @JsonProperty(access = Access.WRITE_ONLY)
  private UserGame toUser;

  @JsonUnwrapped
  private Location location;

  private boolean hit;

  /**
   * THis is the default, no parameter constructor
   */
  public ShotDTO() {
  }

  /**
   * This constructor is used throughout the service package
   * @param shot
   */
  public ShotDTO(Shot shot) {
    toUser = shot.getToUser();
    location = shot.getLocation();
    hit = shot.isHit();
  }

  /**
   * Returns the UserGame of the user being fired upon
   * @return
   */
  public UserGame getToUser() {
    return toUser;
  }

  /**
   * Annotates the user being fired upon
   *
   * @param toUser
   */
  public void setToUser(UserGame toUser) {
    this.toUser = toUser;
  }

  /**
   * Returns the x and y location of the shot itself
   * @return
   */
  public Location getLocation() {
    return location;
  }

  /**
   * Annotates the location of the shot itself
   * @param location
   */
  public void setLocation(Location location) {
    this.location = location;
  }

  /**
   * Returns flag indicating the specific location on a specific board hit a ship
   * @return
   */
  public boolean isHit() {
    return hit;
  }

  /**
   * Annotates the flag
   * @param hit
   */
  public void setHit(boolean hit) {
    this.hit = hit;
  }
}
