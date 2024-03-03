package edu.cnm.deepdive.cutthroatbattleshipservice.service;

import edu.cnm.deepdive.cutthroatbattleshipservice.model.entity.User;
import java.util.Optional;
import java.util.UUID;

public interface AbstractUserService {

  User getOrCreate(String oauthKey, String displayName);

  User getCurrentUser();

  User updateUser(User received);

  Optional<User> get(UUID key, User requester);

}
