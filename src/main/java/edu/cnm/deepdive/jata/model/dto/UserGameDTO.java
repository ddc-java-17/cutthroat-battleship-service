package edu.cnm.deepdive.jata.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.cnm.deepdive.jata.model.entity.Shot;
import edu.cnm.deepdive.jata.model.entity.User;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class acts as an interface to the outside world for the UserGame entity.  This limits the
 * data leakage by not having fields and columns not needed by the user.  The visibility of ships is
 * limited by the usersBoard field.  IF it isn't the user's board, ships sill not be sent.
 */
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

  /**
   * Default, no parameter constructor
   */
  public UserGameDTO() {
  }


  /**
   * Constructor used throughout the service class to join UserGame with the outside world
   *
   * @param userGame
   * @param currentUserGame
   */
  public UserGameDTO(UserGame userGame, UserGame currentUserGame) {
    user = userGame.getUser();
    usersBoard = userGame.equals(currentUserGame);
    ships = ShipDTO.fromLocations(userGame.getLocations());
    toShots = userGame.getToShots().stream()
        .map(ShotDTO::new)
        .toList();
    inventoryPlaced = userGame.isInventoryPlaced();
    fleetSunk = userGame.isFleetSunk();
  }

  /**
   * Returns the user
   *
   * @return
   */
  public User getUser() {
    return user;
  }

  /**
   * Annotates the user
   *
   * @param user
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Returns a list of shipDTOs to the user based on if they are looking at their own ships.
   *
   * @return
   */
  public List<ShipDTO> getShips() {
    return isUsersBoard() ? ships: null;
  }

  /**
   * Annotates the list of ships for a given user
   * @param ships
   */
  public void setShips(List<ShipDTO> ships) {
    this.ships = ships;
  }

  /**
   * Returns a list of shots fired at players
   *
   * @return
   */
  public List<ShotDTO> getToShots() {
    return toShots;
  }

  /**
   * Annotates the list of shots fired at players
   * @param toShots
   */
  public void setToShots(List<ShotDTO> toShots) {
    this.toShots = toShots;
  }

  /**
   * Returns a flag indicating the fleet has been placed.  Used to prohibit users
   * from instantiating multiple fleets
   *
   * @return
   */
  public boolean isInventoryPlaced() {
    return inventoryPlaced;
  }

  /**
   * Annotates the inventoryPlaced Flag
   *
   * @param inventoryPlaced
   */
  public void setInventoryPlaced(boolean inventoryPlaced) {
    this.inventoryPlaced = inventoryPlaced;
  }

  /**
   * Returns the status of the fleetSunk flag.  This is set when a specific userGame's fleet
   * has been sunk
   *
   * @return
   */
  public boolean isFleetSunk() {
    return fleetSunk;
  }

  /**
   * Annotoates the fleetSunk flag
   *
   * @param fleetSunk
   */
  public void setFleetSunk(boolean fleetSunk) {
    this.fleetSunk = fleetSunk;
  }

  /**
   * Returns the status of the usersBoard flag.  This is used to determine if the list of ships
   * should be returned
   *
   * @return
   */
  public boolean isUsersBoard() {
    return usersBoard;
  }

  /**
   * Annotates the usersBoard flag
   * @param usersBoard
   */
  public void setUsersBoard(boolean usersBoard) {
    this.usersBoard = usersBoard;
  }
}
