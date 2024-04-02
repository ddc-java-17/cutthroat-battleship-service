package edu.cnm.deepdive.jata.controller;

import edu.cnm.deepdive.jata.model.entity.ShipLocation;
import edu.cnm.deepdive.jata.model.entity.Shot;
import edu.cnm.deepdive.jata.model.entity.User;
import edu.cnm.deepdive.jata.service.AbstractGameService;
import edu.cnm.deepdive.jata.service.AbstractUserService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *   This is the REST Controller that makes and processes HTTP requests to and from the cloud.
 */
@RestController
@RequestMapping("/games/{gameKey}/ships")
public class ShipLocationController {

  private AbstractGameService gameService;
  private AbstractUserService userService;

  /**
   * This is the constructor for the instance of the controller
   * @param gameService
   * @param userService
   */
  @Autowired
  public ShipLocationController(AbstractGameService gameService, AbstractUserService userService) {
    this.gameService = gameService;
    this.userService = userService;
  }

  /**
   * This is the endpoint that listens for a user to place their ships.  The gameKey parameter
   * is embedded within the path and the fleet of ships is in a json formatted packet in the body
   * @param gameKey
   * @param ships
   * @return
   */
  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ShipLocation> post(
      @PathVariable UUID gameKey,
      @Valid @RequestBody List<ShipLocation> ships) {
    return gameService.submitShips(gameKey, ships, userService.getCurrentUser());
  }

}
