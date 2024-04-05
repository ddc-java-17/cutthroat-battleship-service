package edu.cnm.deepdive.jata.service;

import static edu.cnm.deepdive.jata.model.BoardSize.closestMatch;

import edu.cnm.deepdive.jata.model.BoardSize;
import edu.cnm.deepdive.jata.model.Location;
import edu.cnm.deepdive.jata.model.ShipType;
import edu.cnm.deepdive.jata.model.dto.GameDTO;
import edu.cnm.deepdive.jata.model.dto.ShipDTO;
import edu.cnm.deepdive.jata.model.dto.ShipsDTO;
import edu.cnm.deepdive.jata.model.dao.GameRepository;
import edu.cnm.deepdive.jata.model.dao.ShipLocationRepository;
import edu.cnm.deepdive.jata.model.dao.ShotRepository;
import edu.cnm.deepdive.jata.model.dao.UserGameRepository;
import edu.cnm.deepdive.jata.model.dao.UserRepository;
import edu.cnm.deepdive.jata.model.dto.UserGameDTO;
import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.model.entity.ShipLocation;
import edu.cnm.deepdive.jata.model.entity.Shot;
import edu.cnm.deepdive.jata.model.entity.User;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is where the methods with all the operational and transactional elements live.
 * {@link GameService} implements {@link AbstractGameService} and its methods.  The
 * {@link edu.cnm.deepdive.jata.controller.GameController} invokes the overridden methods here.
 */
@Service
public class GameService implements AbstractGameService {

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
  public GameDTO startJoinGame(Game game, User user) {

    BoardSize boardSize = closestMatch(game.getBoardSize());
    game.setBoardSize(boardSize.getBoardSizeX());
//    List<Game> openGames = gameRepository.findOpenGames(game.getPlayerCount(), user);
    List<Game> openGames = gameRepository.findOpenGames(game.getPlayerCount());

    Game gameToJoin = openGames.isEmpty() ? game : openGames.getFirst();

    UserGame userGame = new UserGame();
    userGame.setGame(gameToJoin);
    userGame.setUser(user);
    userGame.setInventoryPlaced(false);
    gameToJoin.getUserGames().add(userGame);
    gameRepository.save(gameToJoin);

    List<UserGame> totalUserGames = userGameRepository.findUserGamesByGame(gameToJoin);
    userGame.setTurnCount(totalUserGames.size());
    gameToJoin.setTurnCount(userGame.getTurnCount());
    userGameRepository.save(userGame);
    gameRepository.save(gameToJoin);

    GameDTO gameDTO = new GameDTO(gameToJoin);
    UserGameDTO currentDTO = gameDTO.getUserGames().stream()
        .filter((ug)-> ug.getUser().equals(userGame.getUser()))
        .findFirst()
        .orElseThrow();

    Map<ShipType, Integer> inventory = boardSize.getInventory();
    int[] y = {0};
    currentDTO.getShips()
        .getShips()
        .addAll(
        inventory.entrySet()
            .stream()
            .flatMapToInt((entry) -> IntStream.generate(() ->
                    entry.getKey()
                        .getShipSize())
                .limit(entry.getValue()))
            .mapToObj((length) -> {
              ShipDTO shipDTO = new ShipDTO();
              Location loc = new Location(y[0]++, 1);
              shipDTO.setVertical(false);
              shipDTO.setLength(length);
              shipDTO.setOrigin(loc);
              return shipDTO;
            })
            .toList()
    );


    return gameDTO;
  }

  @Override
  public Game getGame(UUID key, User user) {

    return gameRepository
        .findGameByKeyAndUserGamesUser(key, user)
        .orElseThrow();
  }


}


