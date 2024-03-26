package edu.cnm.deepdive.jata.model.dao;

import edu.cnm.deepdive.jata.model.entity.User;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This repository manages user objects and retrieves information from User objects.
 */
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * This method finds the user object by OauthKey
   * @param oauthKey  OauthKey string type.
   * @return user     User object.
   */
  Optional<User> findUserByOauthKey(String oauthKey);

  /**
   * This method fins the user object by its UUID
   * @param key   UUID key. Secure identification to a particular user
   * @return user User object
   */
  Optional<User> findUserByKey(UUID key);

  /**
   * This method finds a user object by its displayName
   * @param displayName Display name of type String
   * @return user       User object
   */
  Optional<User> findUserByDisplayName(String displayName);

  /**
   * This method finds a user object by the UserGame object it points to.
   * @param userGame  UserGame object
   * @return user     User object
   */
  Optional<User> findUserByUserGameContains(UserGame userGame);

}
