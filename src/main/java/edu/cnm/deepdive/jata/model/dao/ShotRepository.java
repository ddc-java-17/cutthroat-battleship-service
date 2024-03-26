package edu.cnm.deepdive.jata.model.dao;

import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.model.entity.Shot;
import edu.cnm.deepdive.jata.model.entity.User;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This repository manages shot objects and receives information from shot objects.
 */
public interface ShotRepository extends JpaRepository<Shot, Long> {

  /**
   * This is used to find shots by the user who sent them.
   * @param fromUser
   * @return Shot object
   */
  Optional<Shot> findShotsByFromUser(UserGame fromUser);

  /**
   * This is used to find shoyts by the user they were fired at.
   * @param toUser
   * @return Shot object
   */
  Optional<Shot> findShotsByToUser(UserGame toUser);

}
