package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.dao.GameRepository;
import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService implements AbstractGameService {

  private final GameRepository gameRepository;

  @Autowired
  public GameService(
      GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  @Override
  public Game startGame(Game game, User user) {
    int[] pool = game
        .getBoardPool()
        .codePoints()
        .distinct()
        .toArray();
    game.setBoardPool(new String(pool, 0, pool.length));
//    game.setUser(user);
    return gameRepository.save(game);
  }

}
