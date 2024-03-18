package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.model.entity.User;
import java.util.UUID;

public interface AbstractGameService {

  Game startGame(Game game, User user);


}
