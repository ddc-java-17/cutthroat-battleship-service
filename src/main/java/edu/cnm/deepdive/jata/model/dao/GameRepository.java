package edu.cnm.deepdive.jata.model.dao;

import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.model.entity.User;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GameRepository extends JpaRepository<Game, Long> {

  Optional<Game> findGameByKey(UUID key);

  @Query("SELECT g FROM Game AS g JOIN g.userGames AS ug WHERE g.key = :key AND ug.user = :user")
  Optional<Game> findGameByKeyAndUserGamesUser(UUID key, User user);

}
