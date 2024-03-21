package edu.cnm.deepdive.jata.controller;

import edu.cnm.deepdive.jata.model.entity.User;
import edu.cnm.deepdive.jata.service.AbstractUserService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final AbstractUserService userService;

  @Autowired
  public UserController(AbstractUserService userService) {
    this.userService = userService;
  }

  @GetMapping(path = "/me")
  public User get() {
    return userService.getCurrentUser();
  }

  @PutMapping(path = "/me")
  public User put() {
    return userService.updateUser();
    // FIXME: 3/21/2024 Not sure that this method needs. Figure it out.
  }

  @GetMapping(path = "/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
  public User getUser(@PathVariable UUID key) {
    return userService.getUser(key);
  }

}
