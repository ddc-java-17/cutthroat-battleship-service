package edu.cnm.deepdive.jata.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.cnm.deepdive.jata.model.entity.Shot;
import edu.cnm.deepdive.jata.model.entity.User;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import java.util.List;
import java.util.stream.Collectors;

public class UserGameDTO {

  @JsonProperty(value = "player")
  private User user;

  private List<ShipDTO> ships;

  private  List<ShotDTO> toShots;

  @JsonProperty(value = "placed")
  private boolean inventoryPlaced;

  private boolean fleetSunk;

  @JsonProperty(value = "mine")
  private boolean usersBoard;

  public UserGameDTO() {
  }

  public UserGameDTO(UserGame userGame, UserGame currentUserGame) {
    user = userGame.getUser();
    ships = ShipDTO.fromLocations(userGame.getLocations());
    toShots = userGame.getToShots().stream()
        .map(ShotDTO::new)
        .toList();
    inventoryPlaced = userGame.isInventoryPlaced();
    fleetSunk = userGame.isFleetSunk();
    usersBoard = userGame.equals(currentUserGame);
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<ShipDTO> getShips() {
    return isUsersBoard() ? ships: null;
  }

  public void setShips(List<ShipDTO> ships) {
    this.ships = ships;
  }

  public List<ShotDTO> getToShots() {
    return toShots;
  }

  public void setToShots(List<ShotDTO> toShots) {
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

  public boolean isUsersBoard() {
    return usersBoard;
  }

  public void setUsersBoard(boolean usersBoard) {
    this.usersBoard = usersBoard;
  }
}
