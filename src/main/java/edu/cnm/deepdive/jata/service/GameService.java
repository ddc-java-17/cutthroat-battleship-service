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
      // Test code that doesn't check for same user multiple times in one game
//    List<Game> openGames = gameRepository.findOpenGames(game.getPlayerCount());

      Game gameToJoin = openGames.isEmpty() ? game : openGames.getFirst();

      UserGame userGame = new UserGame();
      userGame.setGame(gameToJoin);
      userGame.setUser(user);
      userGame.setInventoryPlaced(false);
      gameToJoin.getUserGames().add(userGame);
      userGame.setTurnCount(game.getUserGames().size() - 1);
      gameToJoin.setTurnCount(userGame.getTurnCount());
      gameRepository.save(gameToJoin);

      gameDTO = new GameDTO(gameToJoin, userGame);
      UserGameDTO currentDTO = gameDTO.getUserGames().stream()
          .filter((ug) -> ug.getUser().equals(userGame.getUser()))
          .findFirst()
          .orElseThrow();

      if (!userGame.isInventoryPlaced()) {
        placeInitial(boardSize, currentDTO);
      }

      game.setFinished((game.getUserGames().stream()
          .filter(UserGame::isFleetSunk)
          .count()) >= game.getPlayerCount() - 1);
    }
    return gameDTO;
  }

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

  @Override
  public GameDTO getGame(UUID key, User user) {
    return userGameRepository.findUserGameByGameKeyAndUser(key, user)
        .map((userGame) -> new GameDTO(userGame.getGame(), userGame))
        .orElseThrow();
  }

  @Override
  public long getTurnCount(UUID key, User user) {
    return gameRepository.getTurnCount(key, user)
        .map((obj) -> ((long) obj[0]))
        .orElseThrow();

  }
}


