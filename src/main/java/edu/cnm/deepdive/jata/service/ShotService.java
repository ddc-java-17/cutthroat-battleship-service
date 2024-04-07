package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.Location;
import edu.cnm.deepdive.jata.model.dao.GameRepository;
import edu.cnm.deepdive.jata.model.dao.ShotRepository;
import edu.cnm.deepdive.jata.model.dao.UserGameRepository;
import edu.cnm.deepdive.jata.model.dto.ShotDTO;
import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.model.entity.Shot;
import edu.cnm.deepdive.jata.model.entity.User;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ShotService implements AbstractShotService {

  private final GameRepository gameRepository;
  private final UserGameRepository userGameRepository;
  private final ShotRepository shotRepository;
  private int shotsAllowed;

  public ShotService(GameRepository gameRepository, UserGameRepository userGameRepository,
      ShotRepository shotRepository) {
    this.gameRepository = gameRepository;
    this.userGameRepository = userGameRepository;
    this.shotRepository = shotRepository;
  }

  @Override
  public List<ShotDTO> submitShots(UUID key, List<ShotDTO> shotsDTO, User currentUser) {
    return gameRepository.findGameByKeyAndUserGamesUser(key, currentUser)
        .map((game) -> {
              Optional<UserGame> fromUserGame = userGameRepository.findUserGameByGameAndUser(game,
                  currentUser);
              if (fromUserGame.orElseThrow().getTurnCount() == game.getTurnCount()) {
                shotsAllowed = (int) gameRepository
                    .findGameByKey(key)
                    .orElseThrow()
                    .getUserGames()
                    .stream().filter(userGame -> !userGame.isFleetSunk())
                    .count();
                shotsDTO.forEach((shotDTO) -> {
                  shotsAllowed--;
                  if (shotsAllowed != 0) {
                    Shot shot = new Shot();
                    shot.setLocation(ValidateShot(game, shotDTO));
                    shot.setToUser(
                        userGameRepository.findUserGameByKeyAndGame(shotDTO.getToUser().getKey(), game)
                            .orElseThrow());
                    shot.setFromUser(
                        fromUserGame.orElseThrow());
                    shotRepository.save(shot);
                  }
                });
                game.setTurnCount(
                    (game.getPlayerCount() >= game.getTurnCount()) ? 1 : game.getTurnCount() + 1);
                while (userGameRepository
                    .findUserGameByGameAndTurnCount(game,
                        game.getTurnCount()).orElseThrow().isFleetSunk()) {
                  game.setTurnCount(game.getTurnCount() + 1);
                }
                return shotRepository.saveAll(shotsDTO);
                game.setCurrentUserGame(
                    userGameRepository.findUserGameByGameKeyAndUser(key, currentUser).orElseThrow());
                gameRepository.save(game);
                return shotRepository.saveAll(shots);
              } else {
                throw new NotYourTurnException("Please wait your turn");
              }
            }
        )
        .orElseThrow();
  }

  private static Location ValidateShot(Game game, ShotDTO shot) throws InvalidShotPlacementException {
    if (shot.getLocation().getX() > game.getBoardSize()
        || shot.getLocation().getY() > game.getBoardSize()) {
      throw new InvalidShotPlacementException("Invalid shot");
    } else {
      return shot.getLocation();
    }
  }


  @Override
  public Shot getShot(UUID key, UUID guessKey, User currentUser) {
    return null;
  }
}
