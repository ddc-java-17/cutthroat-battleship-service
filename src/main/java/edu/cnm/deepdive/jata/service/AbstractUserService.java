package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.entity.User;
import java.util.Optional;
import java.util.UUID;

public interface AbstractUserService {

  User getOrCreate(String oauthKey, String displayName);

  User getCurrentUser();

  User getUser(UUID key);

  User updateUser(User received, User requester);

  Optional<User> get(UUID key, User requester);

}
