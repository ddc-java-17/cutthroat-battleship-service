package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.Direction;
import edu.cnm.deepdive.jata.model.Location;
import edu.cnm.deepdive.jata.model.dao.GameRepository;
import edu.cnm.deepdive.jata.model.dao.ShipLocationRepository;
import edu.cnm.deepdive.jata.model.dao.UserGameRepository;
import edu.cnm.deepdive.jata.model.dto.ShipDTO;
import edu.cnm.deepdive.jata.model.dto.ShipsDTO;
import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.model.entity.ShipLocation;
import edu.cnm.deepdive.jata.model.entity.User;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
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
  public Game submitShips(UUID key, ShipsDTO ships, User currentUser) {
    return gameRepository.findGameByKey(key)
        .flatMap((game) -> userGameRepository.findUserGameByGameKeyAndUser(key, currentUser)
            .map(userGame -> {
              int shipCount = shipLocationRepository.findShipLocationByUserGame(userGame)
                  .getCount();
              if (shipCount > 0) {
                throw new FleetAlreadyExistsException(
                    "You have already placed your ships");
              }
              int[] shipNumber = {0};
              Collection<ShipLocation> locations = ships.getShips().stream()
                  .flatMap((ship) -> ship.tovalidshiplocations(game.getBoardSize(),
                      ++shipNumber[0]))
                  .toList();
              Set<ShipLocation> distinctLocations = new HashSet<>(locations);
              if (distinctLocations.size() < locations.size()) {
                throw new InvalidShipLocationException();
              }
              locations.forEach((loc) -> {
                loc.setUserGame(userGame);
                loc.setShipNumber(shipNumber[0]);
              });
              shipLocationRepository.saveAll(locations);
              return game;
            })
        )
        .orElseThrow();
  }

      .

  map((game) ->

  {
    ships.getShips().forEach((ship) -> {
          int index = 1;
          ship.ToValidShipLocations(game.getBoardSize(), index)
              .forEach((loc) -> loc.setUserGame(userGameRepository
                  .findUserGameByGameKeyAndUser(key, currentUser).orElseThrow()));
        })
        .toList();
    return shipLocationRepository.saveAll(ships);
  })
      .

  orElseThrow();
}


}
