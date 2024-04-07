package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.dao.GameRepository;
import edu.cnm.deepdive.jata.model.dao.ShipLocationRepository;
import edu.cnm.deepdive.jata.model.dao.UserGameRepository;
import edu.cnm.deepdive.jata.model.dto.GameDTO;
import edu.cnm.deepdive.jata.model.dto.ShipDTO;
import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.model.entity.ShipLocation;
import edu.cnm.deepdive.jata.model.entity.User;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
  public GameDTO submitShips(UUID key, List<ShipDTO> ships, User currentUser) {
    return userGameRepository.findUserGameByGameKeyAndUser(key, currentUser)
        .map(userGame -> {
          if (userGame.isInventoryPlaced()) {
            throw new FleetAlreadyExistsException(
                "You have already placed your ships");
          }
          int[] shipNumber = {1};
          Game game = userGame.getGame();
          Collection<ShipLocation> locations = ships.stream()
              .flatMap((ship) -> ship.tovalidshiplocations(game.getBoardSize(),
                  shipNumber[0]++))
              .toList();
          Set<ShipLocation> distinctLocations = new HashSet<>(locations);
          if (distinctLocations.size() < locations.size()) {
            throw new InvalidShipLocationException();
          } else {
            locations.forEach((loc) -> {
              loc.setUserGame(userGame);
            });
            shipLocationRepository.saveAll(locations);
            userGame.setInventoryPlaced(true);
            game.setTurnCount(userGame.getTurnCount());
            userGameRepository.save(userGame);
            gameRepository.save(game);
          }
          return new GameDTO(game);
        })
        .orElseThrow();
  }

}
