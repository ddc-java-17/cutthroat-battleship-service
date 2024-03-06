package edu.cnm.deepdive.jata.model.dao;

import edu.cnm.deepdive.jata.model.entity.User;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findUserByOauthKey(String oauthKey);

  Optional<User> findUserByKey(UUID key);

  Optional<User> findUserByDisplayName(String displayName);

  Optional<User> findUserByUserGame(List<UserGame> userGame);


}
