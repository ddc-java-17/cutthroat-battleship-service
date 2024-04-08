package edu.cnm.deepdive.jata.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import edu.cnm.deepdive.jata.model.Location;
import edu.cnm.deepdive.jata.model.entity.Shot;
import edu.cnm.deepdive.jata.model.entity.UserGame;

public class ShotDTO {

  @JsonProperty(access = Access.WRITE_ONLY)
  private UserGame toUser;

  @JsonUnwrapped
  private Location location;

  private boolean hit;


  public ShotDTO() {
  }

  public ShotDTO(Shot shot) {
    toUser = shot.getToUser();
    location = shot.getLocation();
    hit = shot.isHit();
  }

  public UserGame getToUser() {
    return toUser;
  }

  public void setToUser(UserGame toUser) {
    this.toUser = toUser;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public boolean isHit() {
    return hit;
  }

  public void setHit(boolean hit) {
    this.hit = hit;
  }
}
