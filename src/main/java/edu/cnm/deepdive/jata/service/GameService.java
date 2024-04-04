package edu.cnm.deepdive.jata.service;

import static edu.cnm.deepdive.jata.model.BoardSize.closestMatch;

import edu.cnm.deepdive.jata.model.BoardSize;
import edu.cnm.deepdive.jata.model.dto.ShipDTO;
import edu.cnm.deepdive.jata.model.dto.ShipsDTO;
import edu.cnm.deepdive.jata.model.dao.GameRepository;
import edu.cnm.deepdive.jata.model.dao.ShipLocationRepository;
import edu.cnm.deepdive.jata.model.dao.ShotRepository;
import edu.cnm.deepdive.jata.model.dao.UserGameRepository;
import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.model.entity.ShipLocation;
import edu.cnm.deepdive.jata.model.entity.Shot;
import edu.cnm.deepdive.jata.model.entity.User;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is where the methods with all the operational and transactional elements live.
 * {@link GameService} implements {@link AbstractGameService} and its methods.  The
 * {@link edu.cnm.deepdive.jata.controller.GameController} invokes the overridden methods here.
 */
@Service
public class GameService implements AbstractGameService {

  public static final int MAX_SHIPS_PER_PLAYER = 3;
  private final GameRepository gameRepository;
  private final UserGameRepository userGameRepository;
  /**
   * This constructor initializes an instance of {@link GameRepository} that this service class can
   * use.
   *
   * @param gameRepository {@link GameRepository} instance to be initialized.
   */
  @Autowired
  public GameService(
      GameRepository gameRepository, UserGameRepository userGameRepository) {
    this.gameRepository = gameRepository;
    this.userGameRepository = userGameRepository;
  }

  @Override
  public Game startJoinGame(Game game, User user) {
    game.setBoardSize(BoardSize.closestMatch(game.getBoardSize()).getBoardSizeX());
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


}


