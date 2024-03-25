package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.dao.UserRepository;
import edu.cnm.deepdive.jata.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * This class is where the methods with all the operational and transactional elements live.
 * {@link UserService} implements {@link AbstractUserService} and its methods.  The
 * {@link edu.cnm.deepdive.jata.controller.UserController} invokes the overridden methods here.
 */
@Service
public class UserService implements AbstractUserService{

  private final UserRepository userRepository;

  /**
   * This constructor initializes an instance of {@link UserRepository} that this service class can
   * use.
   *
   * @param userRepository {@link UserRepository} instance to be initialized.
   */
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User getOrCreate(String oauthKey, String displayName) {  // TODO: 2/29/2024 Add parameters for additional user profile info from the bearer token
    return userRepository
        .findUserByOauthKey(oauthKey)
        .orElseGet(()->{
          User user = new User();
          user.setOauthKey(oauthKey);
          user.setDisplayName(displayName);
          // TODO: 2/29/2024 Assign any additional fields of user.
          return userRepository.save(user);
        });
  }

  @Override
  public User getCurrentUser() {
    return (User) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
  }

  @Override
  public User getUser(UUID key) {
    return userRepository
        .findUserByKey(key)
        .orElseThrow();
  }

  @Override
  public User updateUser(User received, User requester) {
    return userRepository
        .findById(requester.getId())
        .map((user)->{
          String displayName = received.getDisplayName();
          //noinspection ConstantValue
          if (displayName != null) {
            user.setDisplayName(displayName);
          }
          return userRepository.save(user);
        })
        .orElseThrow();
  }

  @Override
  public Optional<User> get(UUID key, User requester) {
    return userRepository
        .findUserByKey(key);// TODO: 2/29/2024 Apply access control rules as appropriate
  }
}
