package edu.cnm.deepdive.jata.model.dao;

import edu.cnm.deepdive.jata.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findUserByOauthKey(String oauthKey);

  Optional<User> findUserByKey(UUID key);

}
