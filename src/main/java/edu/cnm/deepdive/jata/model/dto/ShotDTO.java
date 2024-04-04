package edu.cnm.deepdive.jata.model.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import edu.cnm.deepdive.jata.model.Location;
import edu.cnm.deepdive.jata.model.entity.UserGame;

public class ShotDTO {


  private UserGame toUser;

  @JsonUnwrapped
  private Location location;


}
