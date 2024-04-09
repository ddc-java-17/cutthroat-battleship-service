package edu.cnm.deepdive.jata.service;

import static edu.cnm.deepdive.jata.model.BoardSize.closestMatch;

import edu.cnm.deepdive.jata.model.BoardSize;
import edu.cnm.deepdive.jata.model.Location;
import edu.cnm.deepdive.jata.model.ShipType;
import edu.cnm.deepdive.jata.model.dao.GameRepository;
import edu.cnm.deepdive.jata.model.dao.UserGameRepository;
import edu.cnm.deepdive.jata.model.dto.GameDTO;
import edu.cnm.deepdive.jata.model.dto.ShipDTO;
import edu.cnm.deepdive.jata.model.dto.UserGameDTO;
import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.model.entity.User;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
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

  /*
   * This constructor initializes an instance of {@link GameRepository} that this service class can
   * use.
   *
   * @param gameRepository {@link GameRepository} instance to be initialized.
   */

  /**
   * This constructor initializes an instance of {@link GameRepository} and
   * {@link UserGameRepository}that this service class can
   * use.
   * @param gameRepository
   * @param userGameRepository
   */
  @Autowired
  public GameService(
      GameRepository gameRepository, UserGameRepository userGameRepository) {
    this.gameRepository = gameRepository;
    this.userGameRepository = userGameRepository;
  }

  /**
   * THis method performs three functions: Start a game that doesn't exist yet, join a game that has
   * already started, and rejoin a game that has started.  The method checks if the user is already in
   * a game and forces the user to finish any game they have started.
   *
   * @param game GameService for a specific game.
   * @param user User for the specified user starting or joining a game.
   * @return
   */
  @Override
  public GameDTO startJoinGame(Game game, User user) {

    BoardSize boardSize = closestMatch(game.getBoardSize());
    game.setBoardSize(boardSize.getBoardSizeX());
    GameDTO gameDTO;
    List<Game> currentGames = gameRepository.findCurrentGames(user);
    if (!currentGames.isEmpty()) {
      Game currentGame = currentGames.getFirst();
      gameDTO = new GameDTO(currentGame,
          userGameRepository.findUserGameByGameAndUser(currentGame, user).orElseThrow());
      List<UserGameDTO> userGames = gameDTO.getUserGames();
      UserGameDTO currentUserGameDTO = userGames.stream()
          .filter((userGame -> userGame.getUser().equals(user)))
          .findFirst().orElseThrow();
      if (!currentUserGameDTO.isInventoryPlaced()) {
        placeInitial(boardSize, currentUserGameDTO);
      }
    } else {
      List<Game> openGames = gameRepository.findOpenGames(game.getPlayerCount(), user);

      Game gameToJoin = openGames.isEmpty() ? game : openGames.getFirst();

      UserGame userGame = new UserGame();
      userGame.setGame(gameToJoin);
      userGame.setUser(user);
      userGame.setInventoryPlaced(false);
      gameToJoin.getUserGames().add(userGame);
      userGame.setTurnCount(gameToJoin.getUserGames().size() - 1);
      gameToJoin.setTurnCount(userGame.getTurnCount());
      gameToJoin.setFinished((gameToJoin.getUserGames().stream()
          .filter(UserGame::isFleetSunk)
          .count()) >= gameToJoin.getPlayerCount() - 1);
      gameRepository.save(gameToJoin);

      gameDTO = new GameDTO(gameToJoin, userGame);
      UserGameDTO currentDTO = gameDTO.getUserGames().stream()
          .filter((ug) -> ug.getUser().equals(userGame.getUser()))
          .findFirst()
          .orElseThrow();

      if (!userGame.isInventoryPlaced()) {
        placeInitial(boardSize, currentDTO);
      }

    }
    return gameDTO;
  }

  /**
   * This looks up the type and number of ships for a given game and returns the ships in default
   * positions in vector format
   *
   * @param boardSize
   * @param currentDTO
   */
  private static void placeInitial(BoardSize boardSize, UserGameDTO currentDTO) {
    Map<ShipType, Integer> inventory = boardSize.getInventory();
    int[] y = {1};
    currentDTO.getShips()
        .addAll(
            inventory.entrySet()
                .stream()
                .flatMapToInt((entry) -> IntStream.generate(() ->
                        entry.getKey()
                            .getShipSize())
                    .limit(entry.getValue()))
                .mapToObj((length) -> {
                  ShipDTO shipDTO = new ShipDTO();
                  Location loc = new Location(1, y[0]++);
                  shipDTO.setVertical(false);
                  shipDTO.setLength(length);
                  shipDTO.setOrigin(loc);
                  return shipDTO;
                })
                .toList()
        );
  }

  /**
   * This is the status method.
   *
   * @param key UUID
   * @param user User
   * @return
   */
  @Override
  public GameDTO getGame(UUID key, User user) {
    return userGameRepository.findUserGameByGameKeyAndUser(key, user)
        .map((userGame) -> new GameDTO(userGame.getGame(), userGame))
        .orElseThrow();
  }

  /**
   * This method returns the current turn count for a specific game
   * @param key
   * @param user
   * @return
   */
  @Override
  public long getTurnCount(UUID key, User user) {
    return gameRepository.getTurnCount(key, user)
        .map((obj) -> ((long) obj[0]))
        .orElseThrow();

  }
}


