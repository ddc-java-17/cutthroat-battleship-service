package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.model.entity.ShipLocation;
import edu.cnm.deepdive.jata.model.entity.Shot;
import edu.cnm.deepdive.jata.model.entity.User;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import java.util.List;
import java.util.UUID;

public interface AbstractGameService {

  Game startJoinGame(Game game, User user);

  Game getGame(UUID key, User user);

  List<Shot> submitShots(UUID key, List<Shot> shots, User currentUser);

  /**
   * This takes a user's ships and instantiates them into the ShipLocation table
   * @param key
   * @param ships
   * @param currentUser
   * @return
   */
  List<ShipLocation> submitShips(UUID key, List<ShipLocation> ships, User currentUser);

  Shot getShot(UUID key, UUID guessKey, User currentUser);
}
