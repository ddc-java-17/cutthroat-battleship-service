package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.Location;
import edu.cnm.deepdive.jata.model.dao.GameRepository;
import edu.cnm.deepdive.jata.model.dao.ShotRepository;
import edu.cnm.deepdive.jata.model.dao.UserGameRepository;
import edu.cnm.deepdive.jata.model.dto.GameDTO;
import edu.cnm.deepdive.jata.model.dto.ShotDTO;
import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.model.entity.Shot;
import edu.cnm.deepdive.jata.model.entity.User;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ShotService implements AbstractShotService {

  private final GameRepository gameRepository;
  private final UserGameRepository userGameRepository;
  private final ShotRepository shotRepository;
  private int playersRemaining;

  public ShotService(GameRepository gameRepository, UserGameRepository userGameRepository,
      ShotRepository shotRepository) {
    this.gameRepository = gameRepository;
    this.userGameRepository = userGameRepository;
    this.shotRepository = shotRepository;
  }

  @Override
  public GameDTO submitShots(UUID key, List<ShotDTO> shotsDTO, User currentUser) {
    return userGameRepository.findUserGameByGameKeyAndUser(key, currentUser)
        .map((userGame) -> {
              Game game = userGame.getGame();
              int gameTurnCount = (int) game.getTurnCount();
              if (userGame.getTurnCount() == gameTurnCount) {
                List<UserGame> userGames = game.getUserGames();
                playersRemaining = (int) userGames
                    .stream()
                    .filter(userGame1 -> !userGame1.isFleetSunk())
                    .count();
                List<Shot> shots = shotsDTO.subList(0, playersRemaining-1)
                    .stream()
                    .map((shotDTO) -> {
                      Shot shot = new Shot();
                      shot.setLocation(validateShot(game, shotDTO));
                      UserGame toUserGame = userGames.stream()
                          .filter((ug) -> ug.getUser().getKey().equals(shotDTO.getToUser().getKey()))
                          .findFirst()
                          .orElseThrow();
                      shot.setToUser(toUserGame);
                      shot.setFromUser(userGame);
                      toUserGame.getToShots().add(shot);
                      return shot;
                    })
                    .toList();
                userGame.getFromShots().addAll(shots);
                gameRepository.save(game);
                do {
                  gameTurnCount++;
                  gameTurnCount = (gameTurnCount >= game.getPlayerCount()) ? 0 : gameTurnCount;
                } while (userGames.get(gameTurnCount).isFleetSunk());
                if(userGame.equals(userGames.get(gameTurnCount))){
                  game.setFinished(true);
                }
                game.setTurnCount(gameTurnCount);
                return new GameDTO(gameRepository.save(game), userGame);
              } else {
                throw new NotYourTurnException("Please wait your turn");
              }
            }
        )
        .orElseThrow();
  }

  private static Location validateShot(Game game, ShotDTO shot)
      throws InvalidShotPlacementException {
    if (shot.getLocation().getX() > game.getBoardSize()
        || shot.getLocation().getY() > game.getBoardSize()) {
      throw new InvalidShotPlacementException("Invalid shot");
    } else {
      return shot.getLocation();
    }
  }

}
