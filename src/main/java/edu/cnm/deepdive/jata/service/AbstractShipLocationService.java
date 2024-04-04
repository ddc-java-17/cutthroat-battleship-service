package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.dto.ShipsDTO;
import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.model.entity.ShipLocation;
import edu.cnm.deepdive.jata.model.entity.User;
import java.util.Collection;
import java.util.UUID;

public interface AbstractShipLocationService {
  /**
   * This takes a user's ships and instantiates them into the ShipLocation table
   * @param key
   * @param ships
   * @param currentUser
   * @return
   */
  Game submitShips(UUID key, ShipsDTO ships, User currentUser);

}
