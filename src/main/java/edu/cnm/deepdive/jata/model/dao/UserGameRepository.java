package edu.cnm.deepdive.jata.model.dao;

import edu.cnm.deepdive.jata.model.entity.Fleet;
import edu.cnm.deepdive.jata.model.entity.User;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserGameRepository extends JpaRepository<UserGame, Long> {

  List<UserGame> findUserGameByUser(User user);

  Optional<UserGame> findUserGameByKey(UUID key);


  // TODO: 3/4/2024 Figure out how to keep toUser and fromUser distinct,
  //  and possibly combine the next two shot fields

  // TODO: 3/4/2024 @Query for processing shots
  // TODO: 3/4/2024 Query for checking for sunk ships
  // TODO: 3/4/2024 Query for checking for sunk fleets
}
