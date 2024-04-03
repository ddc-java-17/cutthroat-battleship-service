package edu.cnm.deepdive.jata.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.List;

public class ShipsDTO {

  @JsonProperty(access = Access.READ_WRITE)
  private List<ShipDTO> ships;

  public List<ShipDTO> getShips() {
    return ships;
  }

  public void setShips(List<ShipDTO> ships) {
    this.ships = ships;
  }
}
