package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.Direction;
import edu.cnm.deepdive.jata.model.dao.GameRepository;
import edu.cnm.deepdive.jata.model.dao.ShipLocationRepository;
import edu.cnm.deepdive.jata.model.dao.UserGameRepository;
import edu.cnm.deepdive.jata.model.dto.ShipDTO;
import edu.cnm.deepdive.jata.model.dto.ShipsDTO;
import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.model.entity.ShipLocation;
import edu.cnm.deepdive.jata.model.entity.User;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ShipLocationService implements AbstractShipLocationService {

  private final ShipLocationRepository shipLocationRepository;
  private final UserGameRepository userGameRepository;
  private final GameRepository gameRepository;
  private final AbstractGameService gameService;

  private static boolean[][] hits;

  public ShipLocationService(ShipLocationRepository shipLocationRepository,
      UserGameRepository userGameRepository,
      GameRepository gameRepository,
      AbstractGameService gameService) {
    this.shipLocationRepository = shipLocationRepository;
    this.userGameRepository = userGameRepository;
    this.gameRepository = gameRepository;
    this.gameService = gameService;
  }

  /**
   * This method validates the placement of each ship, checking for ships partially, or entirely off
   * of the board, intersecting ships, or users trying to place ships once a fleet has already been
   * placed.  If the validation shows the placement is valid and legal, the ships are saved in the
   * ShipLocation table
   *
   * @param key
   * @param ships
   * @param currentUser
   * @return
   */
  @Override
  public ShipsDTO submitShips(UUID key, ShipsDTO ships, User currentUser) {
    hits = new boolean[gameService.getGame(key, currentUser).getBoardSize()]
        [gameService.getGame(key, currentUser).getBoardSize()];
    if (shipLocationRepository
        .findShipLocationByUserGame(userGameRepository
            .findUserGameByGameKeyAndUser(key, currentUser)
            .orElseThrow())
        .getCount() > 0) {
      throw new FleetAlreadyExistsException("You have already placed your ships");
    }

    return gameRepository.findGameByKeyAndUserGamesUser(key, currentUser)
        .map((game) -> {
          ships.getShips().forEach((ShipDTO ship) -> {
            ValidateShipLocationAndBoardEdge(ship,
                gameRepository.findGameByKey(key).orElseThrow());
             CreateShipLocationTableEntry(
                ship,
                shipLocationRepository
                    .findShipLocationByGameAndUserGame(game,
                        userGameRepository
                            .findUserGameByGameAndUser(game, currentUser).orElseThrow())
                    .orElseThrow(),
                gameRepository.findGameByKey(key).orElseThrow(),
                currentUser);
          });
          return shipLocationRepository.saveAll(ShipDTO);
        })
        .orElseThrow();
  }

  /**
   * This is the validation method for board edge detection and ship intersection detection
   *
   * @param game
   */
  private static void ValidateShipLocationAndBoardEdge(ShipDTO ship, Game game) {
    int[] indexMod;
    indexMod = (ship.isVertical())
        ? new int[]{Direction.VERTICAL.getVerticalIndex(), Direction.VERTICAL.getHorizontalIndex()}
        : new int[]{Direction.HORIZONTAL.getVerticalIndex(),
            Direction.HORIZONTAL.getHorizontalIndex()};

    // test versus board edges
    if ((ship.getShipOriginX() + ship.getShipLength()) > game.getBoardSize()
        || (ship.getShipOriginY() + ship.getShipLength()) > game.getBoardSize()) {
      throw new InvalidShipLocationException("Ships must be placed on the board");
    }
    // test versus other ships
    for (int lengthIndex = 0; lengthIndex < ship.getShipLength(); lengthIndex++) {
      if (hits[ship.getShipOriginY() + lengthIndex * indexMod[0]]
          [ship.getShipOriginX() + lengthIndex * indexMod[1]]) {
        throw new InvalidShipLocationException("Ships must not intersect each other");
      } else {
        hits[ship.getShipOriginY() + lengthIndex * indexMod[0]]
            [ship.getShipOriginX() + lengthIndex * indexMod[1]] = true;
      }
    }
  }


  private void CreateShipLocationTableEntry(ShipDTO ship, ShipLocation location, Game game,
      User currentUser) {
    int[] indexMod;
    indexMod = (ship.isVertical())
        ? new int[]{Direction.VERTICAL.getVerticalIndex(), Direction.VERTICAL.getHorizontalIndex()}
        : new int[]{Direction.HORIZONTAL.getVerticalIndex(),
            Direction.HORIZONTAL.getHorizontalIndex()};

    for (int lengthIndex = 0; lengthIndex < ship.getShipLength(); lengthIndex++) {
      location.setShipCoordY(ship.getShipOriginY() + lengthIndex * indexMod[0]);
      location.setShipCoordX(ship.getShipOriginX() + lengthIndex * indexMod[1]);
      location.setShipNumber(ship.getShipNumber());
      location.setUserGame(
          userGameRepository.findUserGameByGameAndUser(game, currentUser).orElseThrow());
    }
  }

}
