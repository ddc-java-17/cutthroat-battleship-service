package edu.cnm.deepdive.cutthroatbattleshipservice.controller;

import edu.cnm.deepdive.cutthroatbattleshipservice.model.entity.User;
import edu.cnm.deepdive.cutthroatbattleshipservice.service.AbstractUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
  public User get(){
    return userService.getCurrentUser();
  }
}
