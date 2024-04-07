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
    Game startJoinGame;
    GameDTO gameDTO;
    BoardSize boardSize = closestMatch(game.getBoardSize());
    game.setBoardSize(boardSize.getBoardSizeX());
    List<Game> currentGames = gameRepository.findCurrentGames(user);
    if (!currentGames.isEmpty()) {
      startJoinGame = currentGames.getFirst();
      startJoinGame.setCurrentUserGame(
          userGameRepository.findUserGameByGameAndUser(game, user).orElseThrow());
//      gameRepository.save(startJoinGame);
      gameDTO = new GameDTO(startJoinGame);
      List<UserGameDTO> userGames = gameDTO.getUserGames();
      UserGameDTO currentUserGameDTO = userGames.stream()
          .filter((userGame -> userGame.getUser().equals(user)))
          .findFirst().orElseThrow();
      if (!currentUserGameDTO.isInventoryPlaced()) {
        PlaceInitial(boardSize, currentUserGameDTO);
      }
    } else {
      List<Game> openGames = gameRepository.findOpenGames(game.getPlayerCount(), user);
      // Test code that doesn't check for same user multiple times in one game
//    List<Game> openGames = gameRepository.findOpenGames(game.getPlayerCount());

      startJoinGame = openGames.isEmpty() ? game : openGames.getFirst();

      UserGame userGame = new UserGame();
      userGame.setGame(startJoinGame);
      userGame.setUser(user);
      userGame.setInventoryPlaced(false);
      startJoinGame.getUserGames().add(userGame);
      startJoinGame.setCurrentUserGame(userGame);
      startJoinGame.setCurrentUserGame(
          userGameRepository.findUserGameByGameAndUser(game, user).orElseThrow());

      userGame.setTurnCount(game.getUserGames().size());
      userGameRepository.save(userGame);
      gameRepository.save(startJoinGame);

      gameDTO = new GameDTO(startJoinGame);
      UserGameDTO currentDTO = gameDTO.getUserGames().stream()
          .filter((ug) -> ug.getUser().equals(userGame.getUser()))
          .findFirst()
          .orElseThrow();

      PlaceInitial(boardSize, currentDTO);

      game.setFinished((game.getUserGames().stream()
          .filter(UserGame::isFleetSunk)
          .count()) >= game.getPlayerCount() - 1);
    }

    return gameDTO;
  }

  private static void PlaceInitial(BoardSize boardSize, UserGameDTO currentDTO) {
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
    Game game = gameRepository
        .findGameByKeyAndUserGamesUser(key, user)
        .orElseThrow();
    game.setCurrentUserGame(
        userGameRepository.findUserGameByGameKeyAndUser(key, user).orElseThrow());
    return new GameDTO(game);
  }

  @Override
  public long getTurnCount(UUID key, User user) {
    return gameRepository.getTurnCount(key, user)
        .map((obj) -> ((long) obj[0]))
        .orElseThrow();

  }
}


