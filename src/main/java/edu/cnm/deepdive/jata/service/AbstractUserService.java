package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.entity.User;
import java.util.Optional;
import java.util.UUID;

/**
 * This interface is implemented by {@link UserService} and {@link UserService} overrides the
 * methods in this interface.
 */
public interface AbstractUserService {

  /**
   * Gets or creates {@link User} instance when requested.
   *
   * @param oauthKey oauthKey of a given {@code user}.
   * @param displayName Display name of a given {@code user}.
   * @return {@code user}
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
   *
   * @param key {@link UUID}
   * @return user {@link User}
   */
  User getUser(UUID key);

  /**
   * Updates the current {@link User} instance and then sends the updated {@code user} to the cloud.
   *
   * @param received The updated {@link User} instance to be PUT into the cloud.
   * @param requester The {@link User} requesting that the instance be updated.
   * @return user
   */
  User updateUser(User received, User requester);

  /**
   * Gets another user's {@link User} instance from their {@link UUID} {@code key}.
   *
   * @param key {@link UUID} {@code key} belonging to another {@code user}
   * @param requester {@code user} making the request.
   * @return
   */
  Optional<User> get(UUID key, User requester);

}
