package edu.cnm.deepdive.jata.controller;

import edu.cnm.deepdive.jata.model.entity.Shot;
import edu.cnm.deepdive.jata.model.entity.User;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import edu.cnm.deepdive.jata.service.AbstractGameService;
import edu.cnm.deepdive.jata.service.AbstractUserService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a REST controller that makes and processes HTTP requests from the cloud.
 */
@RestController
@RequestMapping("games/{key}/shot")
public class ShotController {

  private final AbstractGameService gameService;
  private final AbstractUserService userService;

  /**
   * This is a constructor for the instance of the controller.
   * @param gameService GameService
   * @param userService UserService
   */
  @Autowired
  public ShotController(AbstractGameService gameService, AbstractUserService userService) {
    this.gameService = gameService;
    this.userService = userService;
  }

  /**
   * This is an endpoint that listens for a list of a user's shots.
   * @param key UUID
   * @param shots Shots
   * @return gameService.submitShots
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Shot> post(@PathVariable UUID key, @Valid @RequestBody List<Shot> shots) {
    return gameService.submitShots(key, shots, userService.getCurrentUser());
//    URI location = WebMvcLinkBuilder.linkTo(
//        WebMvcLinkBuilder.methodOn(ShotController.class)
//            .get(key, newShot.)
//    )
//        .toUri();
//    return ResponseEntity.created(location)
//        .body(newShot);
  }

  /**
   * This is an endpoint that gets the user's shots, it has the game key and shot keys embedded in it.
   * @param key UUID
   * @param shotKey UUID
   * @return gameService.getShot
   */
  @GetMapping(path = "/{shotKey}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Shot get(@PathVariable UUID key, @PathVariable UUID shotKey) {
    return gameService.getShot(key, shotKey, userService.getCurrentUser());
  }

}
