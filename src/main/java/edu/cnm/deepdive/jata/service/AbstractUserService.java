package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.entity.User;
import java.util.Optional;
import java.util.UUID;

/**
 *
 */
public interface AbstractUserService {

  /**
   * Gets or creates {@link User} instance when requested
   *
   * @param oauthKey
   * @param displayName
   * @return
   */
  User getOrCreate(String oauthKey, String displayName);

  /**
   * Gets current {@link User} instance when {@link edu.cnm.deepdive.jata.controller.UserController}
   * processes a GET or PUT request.
   *
   * @return {@code user} The current {@link User} instance.
   */
  User getCurrentUser();

  /**
   * Gets {@link User} instance by {@link UUID}.
   * This method is overridden in {@link UserService}
   *
   * @param key {@link UUID}
   * @return user {@link User}
   */
  User getUser(UUID key);

  /**
   * Updates the current {@link User} instance and then sends the updated {@code user} to the cloud.
   * This method is overridden in {@link UserService}.
   *
   * @param received The updated {@link User} instance to be PUT into the cloud.
   * @param requester The {@link User} requesting that the instance be updated.
   * @return user
   */
  User updateUser(User received, User requester);

  /**
   *
   *
   * @param key
   * @param requester
   * @return
   */
  Optional<User> get(UUID key, User requester);

}
