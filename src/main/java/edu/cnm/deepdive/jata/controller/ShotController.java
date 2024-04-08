package edu.cnm.deepdive.jata.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.cnm.deepdive.jata.model.dto.GameDTO;
import edu.cnm.deepdive.jata.model.dto.ShotDTO;
import edu.cnm.deepdive.jata.model.entity.Shot;
import edu.cnm.deepdive.jata.service.AbstractGameService;
import edu.cnm.deepdive.jata.service.AbstractShotService;
import edu.cnm.deepdive.jata.service.AbstractUserService;
import edu.cnm.deepdive.jata.view.ShotView;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@RequestMapping("games/{key}/shots")
public class ShotController {

  private final AbstractShotService shotService;
  private final AbstractUserService userService;

  /**
   * This is a constructor for the instance of the controller.
   * @param gameService GameService
   * @param userService UserService
   */
  @Autowired
  public ShotController(AbstractShotService shotService, AbstractGameService gameService, AbstractUserService userService) {
    this.shotService = shotService;
    this.userService = userService;
  }

  /**
   * This is an endpoint that listens for a list of a user's shots.
   * @param key UUID
   * @param shots Shots
   * @return gameService.submitShots
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public GameDTO post(@PathVariable UUID key, @Valid @RequestBody List<ShotDTO> shots) {
    return shotService.submitShots(key, shots, userService.getCurrentUser());
//    URI location = WebMvcLinkBuilder.linkTo(
//        WebMvcLinkBuilder.methodOn(ShotController.class)
//            .get(key, newShot.)
//    )
//        .toUri();
//    return ResponseEntity.created(location)
//        .body(newShot);
  }

}
