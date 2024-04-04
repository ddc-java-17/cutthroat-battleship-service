package edu.cnm.deepdive.jata.controller;

import edu.cnm.deepdive.jata.model.dto.ShipsDTO;
import edu.cnm.deepdive.jata.service.AbstractGameService;
import edu.cnm.deepdive.jata.service.AbstractShipLocationService;
import edu.cnm.deepdive.jata.service.AbstractUserService;
import edu.cnm.deepdive.jata.service.ShipLocationService;
import jakarta.validation.Valid;
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

  private AbstractShipLocationService locationService;
  private AbstractUserService userService;

  /**
   * This is the constructor for the instance of the controller
   */
  @Autowired
  public ShipLocationController(AbstractShipLocationService locationService, AbstractUserService userService) {
    this.locationService = locationService;
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
  public ShipsDTO post(
      @PathVariable UUID gameKey,
      @Valid @RequestBody ShipsDTO ships) {
    return locationService.submitShips(gameKey, ships, userService.getCurrentUser());
  }

}
