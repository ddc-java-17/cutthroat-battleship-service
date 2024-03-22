package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.dao.GameRepository;
import edu.cnm.deepdive.jata.model.dao.ShotRepository;
import edu.cnm.deepdive.jata.model.dao.UserGameRepository;
import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.model.entity.Shot;
import edu.cnm.deepdive.jata.model.entity.User;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService implements AbstractGameService {

  private final GameRepository gameRepository;
  private final UserGameRepository userGameRepository;
  private final ShotRepository shotRepository;

  @Autowired
  public GameService(
      GameRepository gameRepository, UserGameRepository userGameRepository,
      ShotRepository shotRepository) {
    this.gameRepository = gameRepository;
    this.userGameRepository = userGameRepository;
    this.shotRepository = shotRepository;
  }

  @Override
  public Game startJoinGame(Game game, User user) {
    int[] pool = game
        .getBoardPool()
        .codePoints()
        .distinct()
        .toArray();
    game.setBoardPool(new String(pool, 0, pool.length));
    UserGame userGame = new UserGame();
    userGame.setGame(game);
    userGame.setUser(user);
    game.getUserGames().add(userGame);
//    game.setUser(user);
    return gameRepository.save(game);
  }


  @Override
  public Game getGame(UUID key, User user) {
    return gameRepository
        .findGameByKeyAndUserGamesUser(key, user)
        .orElseThrow();
  }

  @Override
  public List<Shot> submitShots(UUID key, List<Shot> shots, User currentUser) {
    return gameRepository.findGameByKeyAndUserGamesUser(key, currentUser)
        .map((game) -> {
          shots.forEach((shot) -> {
            // if (shot.getxCoord() > boardpool.height || shot.getxCoord() < boardpool.height) throw exception; ycoord < || > boardpool.width
            shot.setToUser(userGameRepository.findUserGameByKeyAndGame(shot.getToUser().getKey(), game).orElseThrow());
            shot.setFromUser(userGameRepository.findUserGameByGameAndUser(game, currentUser).orElseThrow());
          });
          return shotRepository.saveAll(shots);
        })
        .orElseThrow();
  }

  @Override
  public Shot getShot(UUID key, UUID guessKey, User currentUser) {
    return null;
  }



}
