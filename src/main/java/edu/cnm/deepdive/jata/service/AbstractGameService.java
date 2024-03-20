package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.model.entity.Shot;
import edu.cnm.deepdive.jata.model.entity.User;
import java.util.UUID;

public interface AbstractGameService {

  Game startJoinGame(Game game, User user);

  Game getGame(UUID key, User user);

  Shot submitShot(UUID key, Shot shot, User currentUser);

  Shot getShot(UUID key, UUID guessKey, User currentUser);
}
