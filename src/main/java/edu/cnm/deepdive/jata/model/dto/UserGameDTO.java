package edu.cnm.deepdive.jata.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.cnm.deepdive.jata.model.entity.Shot;
import edu.cnm.deepdive.jata.model.entity.User;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import java.util.List;

public class UserGameDTO {

  @JsonProperty(value = "player")
  private User user;

  private List<ShipDTO> ships;

  private  List<Shot> toShots;

  @JsonProperty(value = "placed")
  private boolean inventoryPlaced;

  private boolean fleetSunk;

  public UserGameDTO() {
  }

  public UserGameDTO(UserGame userGame) {
    user = userGame.getUser();
    ships = ShipDTO.fromLocations(userGame.getLocations());
    toShots = userGame.getToShots();
    inventoryPlaced = userGame.isInventoryPlaced();
    fleetSunk = userGame.isFleetSunk();
  }



  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<ShipDTO> getShips() {
    return ships;
  }

  public void setShips(List<ShipDTO> ships) {
    this.ships = ships;
  }

  public List<Shot> getToShots() {
    return toShots;
  }

  public void setToShots(List<Shot> toShots) {
    this.toShots = toShots;
  }

  public boolean isInventoryPlaced() {
    return inventoryPlaced;
  }

  public void setInventoryPlaced(boolean inventoryPlaced) {
    this.inventoryPlaced = inventoryPlaced;
  }

  public boolean isFleetSunk() {
    return fleetSunk;
  }

  public void setFleetSunk(boolean fleetSunk) {
    this.fleetSunk = fleetSunk;
  }
}
