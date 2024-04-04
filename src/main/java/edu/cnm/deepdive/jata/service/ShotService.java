package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.dao.GameRepository;
import edu.cnm.deepdive.jata.model.dao.ShotRepository;
import edu.cnm.deepdive.jata.model.dao.UserGameRepository;
import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.model.entity.Shot;
import edu.cnm.deepdive.jata.model.entity.User;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ShotService implements AbstractShotService{

  private final GameRepository gameRepository;
  private final UserGameRepository userGameRepository;
  private final ShotRepository shotRepository;

  public ShotService(GameRepository gameRepository, UserGameRepository userGameRepository,
      ShotRepository shotRepository) {
    this.gameRepository = gameRepository;
    this.userGameRepository = userGameRepository;
    this.shotRepository = shotRepository;
  }


  @Override
  public List<Shot> submitShots(UUID key, List<Shot> shots, User currentUser) {
    return gameRepository.findGameByKeyAndUserGamesUser(key, currentUser)
        .map((game) -> {
          shots.forEach((shot) -> {
            ValidateShot(game, shot);
            shot.setToUser(
                userGameRepository.findUserGameByKeyAndGame(shot.getToUser().getKey(), game)
                    .orElseThrow());
            shot.setFromUser(
                userGameRepository.findUserGameByGameAndUser(game, currentUser).orElseThrow());
          });
          return shotRepository.saveAll(shots);
        })
        .orElseThrow();
  }

  private static void ValidateShot(Game game, Shot shot) throws InvalidShotPlacementException {
    if (shot.getShotCoordX() > game.getBoardSize()
        || shot.getShotCoordY() > game.getBoardSize()) {
      throw new InvalidShotPlacementException("Invalid shot");
    }
  }


  @Override
  public Shot getShot(UUID key, UUID guessKey, User currentUser) {
    return null;
  }
}
