package edu.cnm.deepdive.jata.model.dao;

import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.model.entity.Shot;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShotRepository extends JpaRepository<Shot, Long> {

Optional<Shot> findShotsByFromUserAndGame(UserGame fromUser, Game game);

Optional<Shot> findShotsByToUserAndGame(UserGame toUser, Game game);

Optional<Shot> findShotsByGame(Game game);
}
