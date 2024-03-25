package edu.cnm.deepdive.jata.service;

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

@Service
public class GameService implements AbstractGameService {

  public static final int MAX_SHIPS_PER_PLAYER = 3;
  private final GameRepository gameRepository;
  private final UserGameRepository userGameRepository;
  private final ShotRepository shotRepository;
  private final ShipLocationRepository shipLocationRepository;
  private static boolean[][] hits;

  @Autowired
  public GameService(
      GameRepository gameRepository, UserGameRepository userGameRepository,
      ShotRepository shotRepository, ShipLocationRepository shipLocationRepository) {
    this.gameRepository = gameRepository;
    this.userGameRepository = userGameRepository;
    this.shotRepository = shotRepository;
    this.shipLocationRepository = shipLocationRepository;
  }

  @Override
  public Game startJoinGame(Game game, User user) {
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

  @Override
  public List<ShipLocation> submitShips(UUID key, List<ShipLocation> ships, User currentUser) {
    hits = new boolean[getGame(key, currentUser).getBoardSizeX()][getGame(key,
        currentUser).getBoardSizeY()];
    int currentShipNumber = 1;
    int currentShipPoint = 0;
    if (shipLocationRepository
        .findShipLocationByUserGame(userGameRepository
            .findUserGameByGameKeyAndUser(key, currentUser)
            .orElseThrow())
        .getCount() > 0) {
      throw new FleetAlreadyExistsException("You has already placed your ships");
    }

    return gameRepository.findGameByKeyAndUserGamesUser(key, currentUser)
        .map((game) -> {
          ships.forEach((shipLocation) -> {
            if(shipLocation.getShipNumber() == currentShipNumber){
              currentShipPoint++;

            } else{
              currentShipNumber = shipLocation.getShipNumber();
            }
            ValidateShipLocationAndBoardEdge(shipLocation,
                gameRepository.findGameByKey(key).orElseThrow());
            shipLocation.setUserGame(
                userGameRepository.findUserGameByGameAndUser(game, currentUser).orElseThrow());
          });
          return shipLocationRepository.saveAll(ships);
        })
        .orElseThrow();


  }

  private static void ValidateShipLocationAndBoardEdge(ShipLocation location, Game game) {
    // test versus board edges
    if (location.getShipCoordX() > game.getBoardSizeX()
        || location.getShipCoordY() > game.getBoardSizeY()) {
      throw new InvalidShipLocationException("Ships must be placed on the board");
    }
    // test versus other ships
    if (hits[location.getShipCoordX()][location.getShipCoordY()]) {
      throw new InvalidShipLocationException("Ships must not intersect each other");
    } else {
      hits[location.getShipCoordX()][location.getShipCoordY()] = true;
    }
  }

  private static void ValidateLegalShip(ShipLocation location, UserGame userGame, Game game) {
    for(int i = 1; i <= MAX_SHIPS_PER_PLAYER; i++){
      int[] X = location.
    }
  }

  @Override
  public Shot getShot(UUID key, UUID guessKey, User currentUser) {
    return null;
  }
}
