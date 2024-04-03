package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.dto.ShipsDTO;
import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.model.entity.Shot;
import edu.cnm.deepdive.jata.model.entity.User;
import java.util.List;
import java.util.UUID;

/**
 * This interface is used by {@Link GameService} and {@Link GameService} overrides the methods in this interface.
 */
public interface AbstractGameService {

  /**
   * Starts or joins {@Link GameService} when requested.
   * @param game {@Link GameService} for a specific game.
   * @param user {@link User} for the specified user starting or joining a game.
   * @return {@Code GameService}
   */
  Game startJoinGame(Game game, User user);

  /**
   * Gets the game key and users currently in that game.
   * @param key {@Link UUID}
   * @param user {@Link User}
   * @return gameKey
   */
  Game getGame(UUID key, User user);

  /**
   * submits shots to a game
   * @param key {@Link UUID}
   * @param shots List<Shots>
   * @param currentUser {@Link user}
   * @return shots
   */
  List<Shot> submitShots(UUID key, List<Shot> shots, User currentUser);

  /**
   * This takes a user's ships and instantiates them into the ShipLocation table
   * @param key
   * @param ships
   * @param currentUser
   * @return
   */
  ShipsDTO submitShips(UUID key, ShipsDTO ships, User currentUser);

  /**
   * Gets shots from current game.
   * @param key
   * @param guessKey
   * @param currentUser
   * @return
   */
  Shot getShot(UUID key, UUID guessKey, User currentUser);
}
