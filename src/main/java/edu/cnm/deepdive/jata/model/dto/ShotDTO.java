package edu.cnm.deepdive.jata.model.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import edu.cnm.deepdive.jata.model.Location;
import edu.cnm.deepdive.jata.model.entity.Shot;
import edu.cnm.deepdive.jata.model.entity.UserGame;

public class ShotDTO {

  private UserGame toUser;

  @JsonUnwrapped
  private Location location;

  private boolean isHit;


  public ShotDTO() {
  }

  public ShotDTO(Shot shot) {
    toUser = shot.getToUser();
    location = shot.getLocation();
    isHit = shot.isHit();
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
    return isHit;
  }

  public void setHit(boolean hit) {
    isHit = hit;
  }
}
