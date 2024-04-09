package edu.cnm.deepdive.jata.model.dao;

import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.model.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This repository manages Game objects and receives information from Game objects.
 */
public interface GameRepository extends JpaRepository<Game, Long> {

  /**
   * this method finds a game object by a game key
   * @param key key string type.
   * @return game Game object
   */
  Optional<Game> findGameByKey(UUID key);

  Optional<Game> findGameByUserGamesIsNotEmpty();

  /**
   * Finds all games that a user has joined that are also not finished.  This will
   * be used to stop users from joining many games and not participating
   * @param user
   * @return
   */
  @Query("SELECT g FROM Game AS g Join g.userGames AS ug WHERE NOT g.finished AND ug.user = :user")
  List<Game> findCurrentGames(User user);

  /**
   * finds all open games of a given required player count.  This is used to match an
   * incoming user with games that have yet to be filled and started.
   * @param reqPlayerCount
   * @param user
   * @return
   */
  @Query("SELECT g FROM Game AS g JOIN g.userGames as ug "
      + "WHERE g.playerCount = :reqPlayerCount AND NOT EXISTS "
      + "(SELECT ug2 FROM UserGame AS ug2 WHERE ug2.game = g AND ug2.user = :user) "
      + "AND NOT g.finished "
      + "GROUP BY g.id "
      + "HAVING COUNT(*) < :reqPlayerCount")
  List<Game> findOpenGames(int reqPlayerCount, User user);

  /**
   * This method finds a game by the gameKey and users
   * @param key key string type
   * @param user User
   * @return Game
   */
  @Query("SELECT g FROM Game AS g JOIN g.userGames AS ug WHERE g.key = :key AND ug.user = :user")
  Optional<Game> findGameByKeyAndUserGamesUser(UUID key, User user);

  /**
   *
   * This finds the turn count of a game where only the game key and the user are known
   * @param key
   * @param user
   * @return
   */
  @Query("SELECT g.turnCount FROM Game AS g JOIN g.userGames AS ug WHERE g.key = :key AND ug.user = :user")
  Optional<Object[]> getTurnCount(UUID key, User user);
}
