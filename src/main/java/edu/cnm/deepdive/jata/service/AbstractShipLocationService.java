package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.dto.GameDTO;
import edu.cnm.deepdive.jata.model.dto.ShipDTO;
import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.model.entity.User;
import java.util.List;
import java.util.UUID;

public interface AbstractShipLocationService {
  /**
   * This takes a user's ships and instantiates them into the ShipLocation table
   * @param key
   * @param ships
   * @param currentUser
   * @return
   */
  GameDTO submitShips(UUID key, List<ShipDTO> ships, User currentUser);

}
