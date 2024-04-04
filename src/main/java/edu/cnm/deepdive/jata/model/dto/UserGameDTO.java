package edu.cnm.deepdive.jata.model.dto;

import edu.cnm.deepdive.jata.model.entity.ShipLocation;
import edu.cnm.deepdive.jata.model.entity.Shot;
import edu.cnm.deepdive.jata.model.entity.User;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import java.util.LinkedList;
import java.util.List;

public class UserGameDTO {

  private User user;

  private ShipsDTO ships;

  private  List<Shot> toShots;

  private boolean inventoryPlaced;

  public UserGameDTO() {
  }

  public UserGameDTO(UserGame userGame) {
    user = userGame.getUser();
    ships = new ShipsDTO(ShipDTO.fromLocations(userGame.getLocations()));
    toShots = userGame.getToShots();
    inventoryPlaced = userGame.isInventoryPlaced();
  }



  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public ShipsDTO getShips() {
    return ships;
  }

  public void setShips(ShipsDTO ships) {
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
}
