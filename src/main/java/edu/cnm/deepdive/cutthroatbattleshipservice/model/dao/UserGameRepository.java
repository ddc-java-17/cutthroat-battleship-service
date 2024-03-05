package edu.cnm.deepdive.cutthroatbattleshipservice.model.dao;

import edu.cnm.deepdive.cutthroatbattleshipservice.model.entity.Fleet;
import edu.cnm.deepdive.cutthroatbattleshipservice.model.entity.User;
import edu.cnm.deepdive.cutthroatbattleshipservice.model.entity.UserGame;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;

public interface UserGameRepository {

  Optional<UserGame> findGameByKey(UUID key);

  Optional<UserGame> findUserByKey(UUID key);

  // TODO: 3/4/2024 Figure out how to keep toUser and fromUser distinct,
  //  and possibly combine the next two shot fields
  Optional<UserGame> findShotByFromUser(User user);

  Optional<UserGame> findShotByToUser(User user);

  Optional<UserGame> findShipByXCoordAndYCoord(int xCoord, int yCoord);

  // TODO: 3/4/2024 @Query for processing shots
  @Query("SELECT ship FROM ship "
      + "JOIN shot.xCoord AND ship.xCoord "
      + "JOIN shot.yCoord AND ship.yCoord "
      + "WHERE shot.xCoord = ship.xCoord "
      + "AND shot.yCoord = ship.yCoord")
  Optional<UserGame> findShipAndShotByXCoordAndYCoord(int xCoord, int yCoord);

  // TODO: 3/4/2024 Query for checking for sunk ships
  // TODO: 3/4/2024 Query for checking for sunk fleets
}
