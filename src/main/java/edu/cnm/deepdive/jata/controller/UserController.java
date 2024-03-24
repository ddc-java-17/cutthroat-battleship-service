package edu.cnm.deepdive.jata.controller;

import edu.cnm.deepdive.jata.model.entity.User;
import edu.cnm.deepdive.jata.service.AbstractUserService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

  private final AbstractUserService userService;

  @Autowired
  public UserController(AbstractUserService userService) {
    this.userService = userService;
  }

  /**
   * This method sends a GET request to the server requesting the current {@link User} instance.
   *
   * @return user
   */
  @GetMapping(path = "/me")
  public User get() {
    return userService.getCurrentUser();
  }

  /**
   *
   * @param user
   * @return
   */
  @PutMapping(path = "/me",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public User put(@RequestBody User user) {
    return userService.updateUser(user, userService.getCurrentUser());
  }

  /**
   *
   * @param key
   * @return
   */
  @GetMapping(path = "/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
  public User getUser(@PathVariable UUID key) {
    return userService.getUser(key);
  }



}
