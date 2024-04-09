package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.dto.GameDTO;
import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.model.entity.User;
import java.util.Optional;
import java.util.UUID;

/**
 * This interface is used by GameService overrides the methods in this interface.
 */
public interface AbstractGameService {

  /**
   * Starts or joins game Service when requested.
   *
    * @param game
   * @param user
   * @return
   */
  GameDTO startJoinGame(Game game, User user);

  /**
   * Gets the game key and users currently in that game.
   *
    * @param key
   * @param user
   * @return
   */
  GameDTO getGame(UUID key, User user);

  /**
   * Returns a value of turn count for a given game key and user. Used in the long polling logic
   * of the status end point
   *
   * @param key
   * @param user
   * @return
   */
  long getTurnCount(UUID key, User user);

}
